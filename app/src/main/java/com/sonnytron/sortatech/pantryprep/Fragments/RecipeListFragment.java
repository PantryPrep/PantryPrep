package com.sonnytron.sortatech.pantryprep.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Activity.HomeActivity;
import com.sonnytron.sortatech.pantryprep.Adapters.EndlessRecyclerViewScrollListener;
import com.sonnytron.sortatech.pantryprep.Adapters.RecipeListAdapter;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeQueryInterface;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Models.Query.Match;
import com.sonnytron.sortatech.pantryprep.Models.Query.RecipeQuery;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetails;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeListFragment extends Fragment implements IngredientFilterFragment.onFilterFinishedListener {

    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";


    //recycler view adapter pieces
    private RecipeListAdapter recipeListAdapter;
    private ArrayList<Match> recipes;
    private ArrayList<String> topFiveIngredients;
    private String spiceList = "";
    private int mCurrentPage = 0;


    @BindView(R.id.rvRecipes) RecyclerView rvRecipes;
    @BindView(R.id.ivBackground) ImageView ivBackground;

    Network networkHelper;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkHelper = new Network();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        topFiveIngredients = new ArrayList<>();

        //init the recycler view, retrieve oldest ingredients, then populate recycler.
        initrvRecipes();
        retrieveAllIngredients();
        RetrieveQuery(false);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recipelist_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle action items
        switch (item.getItemId()) {
            //compose selected
            case R.id.mifilterIngredient:
                //launch the actual filter fragment
                LaunchIngredientFilterFragment();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LaunchIngredientFilterFragment() {
        //pass in the 5 ingredients, so we can check it inside the recipe filter
        IngredientFilterFragment nextFrag = new IngredientFilterFragment();

        //pass in the queried 5 ingredients.
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("selected_ingredients", topFiveIngredients);
        nextFrag.setArguments(bundle);

        //launch the fragment as a child fragment.
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, nextFrag).addToBackStack(null).commit();
    }

    @Override
    public void onFilterFinish(ArrayList<String> returnedList) {
        topFiveIngredients = returnedList;
        RetrieveQuery(false); //new search on filter finish
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    //retrieves the ingredients.
    private void retrieveAllIngredients() {
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        List<Ingredient> localTopFiveIngredients = ingredientManager.getTopFiveIngredients();
        List<Ingredient> localSpiceList = ingredientManager.getIngredientsSpiceOnly();

        for (int i = 0; i < localTopFiveIngredients.size(); i++) {
            topFiveIngredients.add(localTopFiveIngredients.get(i).getTitle());
        }

        for (int i = 0; i < localSpiceList.size(); i++) {
            spiceList += localSpiceList.get(i).getTitle() + ",";
            //Log.d("spice: ", localSpiceList.get(i).getTitle());
        }
        //chop comma off
        spiceList = spiceList.substring(0, spiceList.length()-1);
        Log.d("spice: ", spiceList);
    }


    //Retrofit functions
    private void RetrieveQuery(boolean paginate) {
        //do the query if we have internet.
        if (networkHelper.isOnline() && networkHelper.isNetworkAvailable(getActivity())) {
            //http logging ----------------------------------------------
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            //end http logging -------------------------------------------

            Retrofit retrofitAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())  //this piece can be disabled?
                    .build();

            RecipeQueryInterface apiService = retrofitAdapter.create(RecipeQueryInterface.class);
            Call<RecipeQuery> call;

            if (!paginate) {
                ivBackground.setImageDrawable(null);
                recipeListAdapter.clearData();
                recipeListAdapter.notifyDataSetChanged();
                //fire retrofit call based on top 5 ingredients in list.
                call = apiService.getResponse(APP_ID, APP_KEY, spiceList, topFiveIngredients);
            }
            else {
                String currentMax = (mCurrentPage*10) + "";
                call = apiService.getPaginatedResponse(APP_ID, APP_KEY, spiceList, topFiveIngredients,currentMax);
            }
            call.enqueue(new Callback<RecipeQuery>() {
                @Override
                public void onResponse(Call<RecipeQuery> call, retrofit2.Response<RecipeQuery> response) {
                    //get the list of recipes. (matches)
                    List<Match> recipeList = response.body().getMatches();

                    if (recipeList.size() == 0) {
                        Glide.with(getContext())
                                .load(R.drawable.ic_no_recipes_found)
                                .fitCenter()
                                .into(ivBackground);
                    } else {
                        recipes.addAll(recipeList);
                    }
                    recipeListAdapter.notifyDataSetChanged();
                    //Log.d("Retrofit onResponse: ", "retrofit succeed");
                }

                @Override
                public void onFailure(Call<RecipeQuery> call, Throwable t) {
                    Log.d("Retrofit onFailure: ", "retrofit failed");
                }
            });
        } else {
            Log.e("RetrieveQuery: ", "no internet!");
        }
        //dismiss the progress dialog since we're done loading recipes.
        ((HomeActivity) getActivity()).disableProgressDialog();
    }


    //Helper functions -------------------------
    //initializes the recycler view with the recipes.
    private void initrvRecipes() {
        //initialize recipe view list
        recipes = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(getActivity(), recipes);
        rvRecipes.setAdapter(recipeListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRecipes.setLayoutManager(layoutManager);
        rvRecipes.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
                mCurrentPage++;
            }
        });

    }

    public void customLoadMoreDataFromApi(int page) {
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        //  --> Deserialize API response and then construct new objects to append to the adapter
        //  --> Notify the adapter of the changes
        mCurrentPage = page;
        RetrieveQuery(true);
    }

}


