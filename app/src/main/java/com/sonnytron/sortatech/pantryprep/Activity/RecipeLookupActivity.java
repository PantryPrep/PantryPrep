package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;

import com.sonnytron.sortatech.pantryprep.Fragments.NutritionInfoFragment;
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


public class RecipeLookupActivity extends AppCompatActivity {
    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    //ingredient list adapter pieces
    private ArrayAdapter<String> ingredientListAdapter;
    private ArrayList<String> ingredients;
    private String recipeURL;
    private String recipeID;

    Network networkHelper;

    //butterknife binds
    @BindView(R.id.tvYield)
    TextView tvYield;
    @BindView(R.id.lvIngredientList)
    ListView lvIngredientList;
    @BindView(R.id.btnGoRecipe)
    Button btnGoRecipe;
    @BindView(R.id.btnNutrition)
    Button btnNutrition;
    @BindView(R.id.ivRecipeImage)
    ImageView ivRecipeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_detail);
        ButterKnife.bind(this);

        //initialize helper
        networkHelper = new Network();

        //grab the recipe ID
        recipeID = getIntent().getStringExtra("recipe_id");

        //initialize adapter
        ingredients = new ArrayList<>();
        ingredientListAdapter = new ArrayAdapter<>(this, R.layout.custom_recipe_ingredients, ingredients);
        lvIngredientList.setAdapter(ingredientListAdapter);

        if (recipeID == null) {
            Toast.makeText(this, "No recipe ID found! ", Toast.LENGTH_LONG).show();
        } else {
            RetrieveRecipe();
        }

        initRecipeButton(this);
        initNutrButton();

    }


    //Retrofit functions
    private void RetrieveRecipe() {
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
                }

                @Override
                public void onFailure(Call<RecipeDetails> call, Throwable t) {
                    //Log.d("Retrofit onFailure: ", call..toString());
                    t.printStackTrace();
                    Log.e("Retrofit Failure: ", t.toString(), t);
                }
            });
        } else {
            Toast.makeText(this,"Internet currently unavailable, please retry later", Toast.LENGTH_LONG).show();
            Log.e("RetrieveQuery: ", "no internet!");
        }
    }

    //Helper functions----------------------------------------------------
    private void populateFields(RecipeDetails recipeDetails) {
        //set toolbar title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.tvToolbarTitle);
        title.setText(recipeDetails.getName());

        //yields
        tvYield.setText("Makes " + recipeDetails.getNumberOfServings() + " serving(s)");

        //if we have an image, print it.
        if (recipeDetails.getImages().size() > 0) {
            String mediumUrl = recipeDetails.getImages().get(0).getHostedMediumUrl();
            String largeUrl = recipeDetails.getImages().get(0).getHostedLargeUrl();

            //populate adapter with list of ingredients.
            ingredients.addAll(recipeDetails.getIngredientLines());
            ingredientListAdapter.notifyDataSetChanged();

            //prefer medium size, else use big.  don't use small.
            if (largeUrl != null) {
                Glide.with(this)
                        .load(largeUrl)
                        .placeholder(R.mipmap.ic_food_placeholder)
                        .fitCenter()
                        .into(ivRecipeImage);
            } else if (mediumUrl != null) {
                Glide.with(this)
                        .load(mediumUrl)
                        .placeholder(R.mipmap.ic_food_placeholder)
                        .fitCenter()
                        .into(ivRecipeImage);
            } else {
                Glide.with(this)
                        .load(R.mipmap.ic_food_placeholder)
                        .fitCenter()
                        .into(ivRecipeImage);
            }

            //get the recipe location
            recipeURL = recipeDetails.getSource().getSourceRecipeUrl();
        }
    }

    private void initRecipeButton(final Context context) {
        btnGoRecipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();

                        //launch in webview
                        Intent i = new Intent(context, ViewRecipeActivity.class);
                        i.putExtra("recipe_url", recipeURL);
                        startActivity(i);
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void initNutrButton() {
        btnNutrition.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        //launch fragment
                        FragmentManager fm = getSupportFragmentManager();
                        Bundle bundle = new Bundle();

                        NutritionInfoFragment nutriFrag = new NutritionInfoFragment();
                        bundle.putString("recipeID", recipeID);
                        nutriFrag.setArguments(bundle);
                        nutriFrag.show(fm, "fragment_nutrition");
                        break;
                    }
                }
                return false;
            }
        });
    }
}
