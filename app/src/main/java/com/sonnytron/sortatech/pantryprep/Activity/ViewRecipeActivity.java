package com.sonnytron.sortatech.pantryprep.Activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sonnytron.sortatech.pantryprep.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewRecipeActivity extends AppCompatActivity {

    @BindView(R.id.wvRecipePage) WebView wvRecipePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        ButterKnife.bind(this);

        String recipeURL = getIntent().getStringExtra("recipe_url");
        Log.d("onCreate: ", recipeURL);

        wvRecipePage.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("API", "API19");
                view.loadUrl(url);
                return true;
            }

            //for API 24+
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d("API", "API21+");
                //view.loadUrl(request.getUrl().getHost());
                return true;
            }
        });

        wvRecipePage.loadUrl(recipeURL);
    }
}
