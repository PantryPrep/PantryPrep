package com.sonnytron.sortatech.pantryprep.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by Steve on 8/19/2016.
 */
public class ProgressDialogHelper {

    private ProgressDialog pd;

    public void launchProgressDialog(Context context){
        pd = new ProgressDialog(context);
        pd.setTitle("Loading Recipes...");
        pd.setMessage("nom nom nom...");
        pd.setCancelable(false);
        pd.show();
    }

    public void disableProgressDialog(){
        if (pd != null) {
            pd.dismiss();
        }
        else {
            Log.d("disableProgressDialog", "showProgressDialog: pd was null when trying to dismiss progress");
        }
    }
}
