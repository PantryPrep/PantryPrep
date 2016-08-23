package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeDetailInterface;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.Image;
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
import retrofit2.Response;
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
    @BindView(R.id.ivRecipeImage) ImageView ivRecipeImage;

    //ingredient list adapter pieces
    private ArrayAdapter<String> ingredientListAdapter;
    private ArrayList<String> ingredients;

    private String recipeID;
    private String recipeURL;

    Network networkHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_lookup);
        ButterKnife.bind(this);

        //initialize helper
        networkHelper = new Network();

        //retrieve ID, maybe check if null.
        recipeID = getIntent().getStringExtra("recipe_id");

        //lookup your current inventory from sqlLite
        //take your inventory and search top 3/top 5 ingredients
        //do a query on yummly (search)
        //with the response from yummly, do a query for possible recipes.

        //retrieve recipe, now that ID is set.
        if (recipeID != null) {
            RetrieveRecipe();
        } else {
            //something went wrong, abort.
            Toast.makeText(this, "No recipe ID found! ", Toast.LENGTH_LONG).show();
            finish();
        }

        //initialize ingredients list
        ingredients = new ArrayList<>();
        ingredientListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
        lvIngredientList.setAdapter(ingredientListAdapter);

        //test();
        initRecipeButton();
    }

    //Retrofit functions
    private void RetrieveRecipe(){

        //do the query if we have internet.
        if (networkHelper.isOnline() && networkHelper.isNetworkAvailable(this)) {
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
                Intent i = new Intent(getApplicationContext(), ViewRecipeActivity.class);
                i.putExtra("recipe_url", recipeURL);
                startActivity(i);
                //Log.d("recipe_url", recipeURL);
            }
        });
    }

    private void test(){
        ingredients.add("ingredient 1");
        ingredients.add("ingredient 2");
        ingredients.add("ingredient 3");
        ingredients.add("ingredient 4");
        ingredients.add("ingredient 5");
        ingredients.add("test 6");
        ingredients.add("ingredient 7");
        ingredientListAdapter.notifyDataSetChanged();

        List<RecipeDetails> testRecipeList = new ArrayList<>();

        RecipeDetails testRecipe = new RecipeDetails();

        List<Image> image = new ArrayList<>();
        Image newImage = new Image();
        newImage.setHostedLargeUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s360");
        newImage.setHostedMediumUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s180");
        newImage.setHostedSmallUrl("https://lh3.googleusercontent.com/SmMH75Cus5LQdTgzWTTAyWU0YFSglzvjBrAK7PnybFZO9fCqmF7AgivpXRHDK-f3fT_cHf6oZwpweYKjsqOPKTo=s90");
        image.add(newImage);
        //testRecipe.setImages(image);
        testRecipe.setName("German Potato Soup");

        testRecipeList.add(testRecipe);
    }
}
