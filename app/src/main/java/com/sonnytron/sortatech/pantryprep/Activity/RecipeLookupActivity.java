package com.sonnytron.sortatech.pantryprep.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sonnytron.sortatech.pantryprep.R;

public class RecipeLookupActivity extends AppCompatActivity {

    static final String API_KEY = "3efb080dfe0c83724c37f5a0da27dbe8";
    static final String APP_ID = "d38afabf";
    static final String BASE_URL = "http://api.yummly.com/v1/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_lookup);

        //lookup your current inventory from sqlLite
        //take your inventory and search top 3/top 5 ingredients
        //do a query on yummly (search)
        //with the response from yummly, do a query for possible recipes.

    }
}
