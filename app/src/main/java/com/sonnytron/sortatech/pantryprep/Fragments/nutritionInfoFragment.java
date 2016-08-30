package com.sonnytron.sortatech.pantryprep.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Activity.RecipeLookupActivity;
import com.sonnytron.sortatech.pantryprep.Helpers.Network;
import com.sonnytron.sortatech.pantryprep.Interfaces.RecipeDetailInterface;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.NutritionEstimate;
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


public class nutritionInfoFragment extends Fragment {

    static final String APP_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    private Network networkHelper;
    private String recipeID;

    @BindView(R.id.tvNutritionFoodTitle)
    TextView tvNutritionFoodTitle;
    @BindView(R.id.tvCalorieValue)
    TextView tvCalorieValue;
    @BindView(R.id.tvFatCalValue)
    TextView tvFatCalValue;
    @BindView(R.id.tvFatValue)
    TextView tvFatValue;
    @BindView(R.id.tvCholesterolValue)
    TextView tvCholesterolValue;
    @BindView(R.id.tvSodiumValue)
    TextView tvSodiumValue;
    @BindView(R.id.tvProteinValue)
    TextView tvProteinValue;
    @BindView(R.id.tvCalciumValue)
    TextView tvCalciumValue;
    @BindView(R.id.tvIronValue)
    TextView tvIronValue;
    @BindView(R.id.tvFiberValue)
    TextView tvFiberValue;
    @BindView(R.id.tvTotCarbValue)
    TextView tvTotCarbValue;
    @BindView(R.id.tvSugarValue)
    TextView tvSugarValue;
    @BindView(R.id.tvVitB12Value)
    TextView tvVitB12Value;
    @BindView(R.id.tvVitAValue)
    TextView tvVitAValue;
    @BindView(R.id.tvVitCValue)
    TextView tvVitCValue;
    @BindView(R.id.tvVitEValue)
    TextView tvVitEValue;
    @BindView(R.id.tvVitKValue)
    TextView tvVitKValue;


    public nutritionInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkHelper = new Network();
        recipeID = ((RecipeLookupActivity) getActivity()).getRecipeID();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition_info, container, false);
        ButterKnife.bind(this, view);

        if (recipeID == null) {
            Toast.makeText(getContext(), "No recipe ID found! ", Toast.LENGTH_LONG).show();
        } else {
            RetrieveRecipe();
        }

        return view;
    }

    //Retrofit functions
    private void RetrieveRecipe() {

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
                        Log.e("Retrofit onResponse: ", "Nutrition response failed");
                    }

                    Log.d("Retrofit onResponse: ", "Nutrition succeed");
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

    private void populateFields(RecipeDetails recipeDetails) {
        tvNutritionFoodTitle.setText(recipeDetails.getName());
        List<NutritionEstimate> nutritionEstimates = recipeDetails.getNutritionEstimates();

        //loop through and pick out.
        for (NutritionEstimate curItem: nutritionEstimates){
            //Log.d("nutrition item",curItem.getAttribute());
            String curAttribute = curItem.getAttribute();

            switch (curAttribute) {
                case "ENERC_KCAL":
                    tvCalorieValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "FAT_KCAL":
                    tvCalorieValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "CHOLE":
                    tvCholesterolValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "NA":
                    tvSodiumValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "PROCNT":
                    tvProteinValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "CA":
                    tvCalciumValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "FE":
                    tvIronValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "FIBTG":
                    tvFiberValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "FAT":
                    tvFatValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "CHOCDF":
                    tvTotCarbValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "SUGAR":
                    tvSugarValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                //Vitamin section
                case "VITK":
                    tvVitKValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "VITB12":
                    tvVitB12Value.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "VITA_RAE":
                    tvVitAValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "VITC":
                    tvVitCValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;
                case "TOCPHA":
                    tvVitEValue.setText(curItem.getValue() + " " + curItem.getUnit().getName());
                    break;


                default:
                    break;
            }
        }



    }


}
