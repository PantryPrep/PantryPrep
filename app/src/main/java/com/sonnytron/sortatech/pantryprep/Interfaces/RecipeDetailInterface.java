package com.sonnytron.sortatech.pantryprep.Interfaces;

import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Steve on 8/16/2016.
 */
public interface RecipeDetailInterface {

    @GET("recipe/{id}?")
    Call<RecipeDetails> getResponse(@Path("id") String recipeId,
                                    @Query("_app_id") String appId,
                                    @Query("_app_key") String appKey);



}
