package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sonnytron.sortatech.pantryprep.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void recipeActivityHandler(View view)
    {
//        Intent i = new Intent(this, RecipeLookupActivity.class);
//        startActivity(i);
        FragmentManager fm = getSupportFragmentManager();


    }

}
