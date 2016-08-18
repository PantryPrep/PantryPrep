package com.sonnytron.sortatech.pantryprep.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sonnytron.sortatech.pantryprep.Adapters.RecipeListAdapter;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeQueryInterface;
import com.sonnytron.sortatech.pantryprep.Models.Query.Match;
import com.sonnytron.sortatech.pantryprep.Models.Query.RecipeQuery;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.Image;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetail;
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

public class RecipeLookupActivity extends AppCompatActivity {

    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    //butterknife binds
    @BindView(R.id.tvRecipeTitle) TextView tvRecipeTitle;
    @BindView(R.id.tvYield) TextView tvYield;
    @BindView(R.id.lvIngredientList) ListView lvIngredientList;
    @BindView(R.id.btnGoRecipe) Button btnGoRecipe;
    @BindView(R.id.rvRecipes) RecyclerView rvRecipes;

    //recycler view adapter pieces
    private RecipeListAdapter recipeListAdapter;
    private ArrayList<RecipeDetail> recipes;

    //ingredient list adapter pieces
    private ArrayAdapter<String> ingredientListAdapter;
    private ArrayList<String> ingredients;

    Network networkHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_lookup);
        ButterKnife.bind(this);

        //lookup your current inventory from sqlLite
        //take your inventory and search top 3/top 5 ingredients
        //do a query on yummly (search)
        //with the response from yummly, do a query for possible recipes.

        //initialize ingredients list
        ingredients = new ArrayList<>();
        ingredientListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
        lvIngredientList.setAdapter(ingredientListAdapter);

        //inintialize recipe view list
        recipes = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(this, recipes);
        rvRecipes.setAdapter(recipeListAdapter);


        //need a horizontal layout manager.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRecipes.setLayoutManager(layoutManager);

        //initialize helper
        networkHelper = new Network();
        test();
        RetrieveQuery();

    }

    private void RetrieveQuery(){

        if (networkHelper.isOnline() && networkHelper.isNetworkAvailable(this))
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            Retrofit retrofitAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())  //this piece can be disabled?
                    .build();

            RecipeQueryInterface apiService = retrofitAdapter.create(RecipeQueryInterface.class);
            Call<RecipeQuery> call;

            //fire off retrofit call
            call = apiService.getResponse(APP_ID, APP_KEY, "potato cabbage onion");

            call.enqueue(new Callback<RecipeQuery>() {
                @Override
                public void onResponse(Call<RecipeQuery> call, retrofit2.Response<RecipeQuery> response) {
                    //is the response good?
                    //get the list of recipes. (matches)
                    List<Match> recipeList = response.body().getMatches();
                    Log.d("Retrofit onResponse: ", "retrofit succeed");
                }

                @Override
                public void onFailure(Call<RecipeQuery> call, Throwable t) {
                    Log.d("Retrofit onFailure: ", "retrofit failed");
                }
            });
        }
    }

    private void test(){
        ingredients.add("ingredient 1");
        ingredients.add("ingredient 2");
        ingredients.add("ingredient 3");
        ingredients.add("ingredient 4");
        ingredients.add("ingredient 5");
        ingredients.add("ingredient 6");
        ingredients.add("ingredient 7");
        recipeListAdapter.notifyDataSetChanged();

        List<RecipeDetail> testRecipeList = new ArrayList<>();

        RecipeDetail testRecipe = new RecipeDetail();

        List<Image> image = new ArrayList<>();
        Image newImage = new Image();
        newImage.setHostedLargeUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s360");
        newImage.setHostedMediumUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s180");
        newImage.setHostedSmallUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s90");
        image.add(newImage);
        testRecipe.setImages(image);
        testRecipe.setName("German Potato Soup");

        testRecipeList.add(testRecipe);


        recipes.addAll(testRecipeList);
        recipeListAdapter.notifyDataSetChanged();
    }
}
