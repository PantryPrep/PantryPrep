package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Activity.RecipeLookupActivity;
import com.sonnytron.sortatech.pantryprep.Activity.ViewRecipeActivity;
import com.sonnytron.sortatech.pantryprep.Adapters.RecipeListAdapter;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeDetailInterface;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetails;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class recipeDetailFragment extends Fragment {

    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    //butterknife binds
    @BindView(R.id.tvRecipeTitle)    TextView tvRecipeTitle;
    @BindView(R.id.tvYield)    TextView tvYield;
    @BindView(R.id.lvIngredientList)    ListView lvIngredientList;
    @BindView(R.id.btnGoRecipe)    Button btnGoRecipe;
    @BindView(R.id.ivRecipeImage)    ImageView ivRecipeImage;

    //ingredient list adapter pieces
    private ArrayAdapter<String> ingredientListAdapter;
    private ArrayList<String> ingredients;


    private String recipeURL;
    private String recipeID;

    Network networkHelper;


    public recipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeID = ((RecipeLookupActivity)getActivity()).getRecipeID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ButterKnife.bind(this,view);

        //initialize helper
        networkHelper = new Network();



        //lookup your current inventory from sqlLite
        //take your inventory and search top 3/top 5 ingredients
        //do a query on yummly (search)
        //with the response from yummly, do a query for possible recipes.

        //retrieve recipe, now that ID is set.


        //initialize ingredients list
        ingredients = new ArrayList<>();
        ingredientListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ingredients);
        lvIngredientList.setAdapter(ingredientListAdapter);

        if (recipeID == null) {
            Toast.makeText(getContext(), "No recipe ID found! ", Toast.LENGTH_LONG).show();
        } else {
            RetrieveRecipe();
        }

        initRecipeButton();

        return view;
    }

    //Retrofit functions
    private void RetrieveRecipe(){

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

            RecipeDetailInterface apiService = retrofitAdapter.create(RecipeDetailInterface.class);
            Call<RecipeDetails> call = apiService.getResponse(recipeID, APP_ID, APP_KEY);

            call.enqueue(new Callback<RecipeDetails>() {
                @Override
                public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                    //get the list of recipes. (matches)
                    RecipeDetails recipeDetail;

                    if (response.code() == 200) {
                        recipeDetail = response.body();
                        populateFields(recipeDetail);
                    } else {
                        Log.e("Retrofit onResponse: ", "Recipe API response failed");
                    }

                    Log.d("Retrofit onResponse: ", "retrofit succeed");
                }

                @Override
                public void onFailure(Call<RecipeDetails> call, Throwable t) {
                    //Log.d("Retrofit onFailure: ", call..toString());
                    t.printStackTrace();
                    Log.e("Retrofit Failure: ", t.toString(), t);
                }
            });
        } else {
            Log.e("RetrieveQuery: ", "no internet!");
        }
    }


    //Helper functions

    private void populateFields(RecipeDetails recipeDetails){
        tvRecipeTitle.setText(recipeDetails.getName());
        tvYield.setText("Serving Size: " + recipeDetails.getNumberOfServings());

        //if we have an image, print it.
        if (recipeDetails.getImages().size() > 0) {
            String mediumUrl = recipeDetails.getImages().get(0).getHostedMediumUrl();
            String largeUrl = recipeDetails.getImages().get(0).getHostedLargeUrl();

            //populate adapter with list of ingredients.
            ingredients.addAll(recipeDetails.getIngredientLines());
            ingredientListAdapter.notifyDataSetChanged();


            //prefer medium size, else use big.  don't use small.
            if(mediumUrl != null) {
                Glide.with(this)
                        .load(mediumUrl)
                        //.placeholder()
                        .into(ivRecipeImage);
            }
            else if(largeUrl != null)
            {
                Glide.with(this)
                        .load(largeUrl)
                        //.placeholder()
                        .into(ivRecipeImage);
            }
            else
            {
                //load placeholder
            }

            //get the recipe location
            recipeURL = recipeDetails.getSource().getSourceRecipeUrl();
        }
    }

    private void initRecipeButton()
    {
        btnGoRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ViewRecipeActivity.class);
                i.putExtra("recipe_url", recipeURL);
                startActivity(i);
                //Log.d("recipe_url", recipeURL);
            }
        });
    }


}