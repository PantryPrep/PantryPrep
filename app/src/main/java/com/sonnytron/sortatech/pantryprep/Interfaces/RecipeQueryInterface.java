package com.sonnytron.sortatech.pantryprep.Interfaces;

import com.sonnytron.sortatech.pantryprep.Models.Query.RecipeQuery;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Steve on 8/16/2016.
 */
public interface RecipeQueryInterface {

    @GET("recipes?")
    Call<RecipeQuery> getResponse(@Query("_app_id") String appId,
                                  @Query("_app_key") String appKey,
                                  @Query("q") String queryText);
}
