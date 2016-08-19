package com.sonnytron.sortatech.pantryprep.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonnytron.sortatech.pantryprep.Activity.HomeActivity;
import com.sonnytron.sortatech.pantryprep.Adapters.RecipeListAdapter;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeQueryInterface;
import com.sonnytron.sortatech.pantryprep.Models.Query.Match;
import com.sonnytron.sortatech.pantryprep.Models.Query.RecipeQuery;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetails;
import com.sonnytron.sortatech.pantryprep.R;

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


public class RecipeListFragment extends Fragment {

    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    //recycler view adapter pieces
    private RecipeListAdapter recipeListAdapter;
    private ArrayList<Match> recipes;

    @BindView(R.id.rvRecipes) RecyclerView rvRecipes;

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

        //init the recycler view
        initrvRecipes();

        //retrive query and populate recycler
        RetrieveQuery();

        //test function
        //test();

        return view;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    //Retrofit functions
    private void RetrieveQuery(){

        //do the query if we have internet.
        if (networkHelper.isOnline() && networkHelper.isNetworkAvailable(getActivity()))
        {
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

            //fire off retrofit call: test string.
            call = apiService.getResponse(APP_ID, APP_KEY, "potato cabbage onion");

            call.enqueue(new Callback<RecipeQuery>() {
                @Override
                public void onResponse(Call<RecipeQuery> call, retrofit2.Response<RecipeQuery> response) {
                    //get the list of recipes. (matches)
                    List<Match> recipeList = response.body().getMatches();
                    recipes.addAll(recipeList);
                    recipeListAdapter.notifyDataSetChanged();
                    //Log.d("Retrofit onResponse: ", "retrofit succeed");
                }

                @Override
                public void onFailure(Call<RecipeQuery> call, Throwable t) {
                    Log.d("Retrofit onFailure: ", "retrofit failed");
                }
            });
        }
        else
        {
            Log.e("RetrieveQuery: ", "no internet!");
        }

        //dismiss the progress dialog since we're done loading recipes.
        ((HomeActivity)getActivity()).disableProgressDialog();
    }



    //Helper functions -------------------------
    //initializes the recycler view with the recipes.
    private void initrvRecipes()
    {
        //inintialize recipe view list
        recipes = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(getActivity(), recipes);
        rvRecipes.setAdapter(recipeListAdapter);

        //need a horizontal layout manager.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRecipes.setLayoutManager(layoutManager);
    }


    private void test(){
        List<RecipeDetails> testRecipeList = new ArrayList<>();

        List<Match> recipeMatchList = new ArrayList<>();

        Match recipe = new Match();

        List<String> smallUrls = new ArrayList<>();
        smallUrls.add("https://lh3.googleusercontent.com/RIdOImzvbZ11_H58oRPSW6s15AYgO3WrdXFn8yAZ-5_6R_Lvf9F6cy652OL4MHgcmCgfzvoyX1FnArsdfrOxAB8=s90-c");

        recipe.setRecipeName("German potato soup");
        recipe.setSmallImageUrls(smallUrls);
        recipe.setId("tes test  1-2-3");
        recipeMatchList.add(recipe);

        recipes.addAll(recipeMatchList);
        recipeListAdapter.notifyDataSetChanged();

    }




}

