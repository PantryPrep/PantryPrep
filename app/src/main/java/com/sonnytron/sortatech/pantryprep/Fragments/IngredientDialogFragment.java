package com.sonnytron.sortatech.pantryprep.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.Date;

/**
 * Created by sonnyrodriguez on 8/19/16.
 */
public class IngredientDialogFragment extends DialogFragment {
    private EditText etIngredientTitle;
    private String ingredientType;
    private Date expDate;
    private Button btSave;
    private Ingredient mIngredient;

    public IngredientDialogFragment() {

    }

    public static IngredientDialogFragment newInstance(String title) {
        IngredientDialogFragment fragment = new IngredientDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ingredient_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIngredient = new Ingredient();

        etIngredientTitle = (EditText) view.findViewById(R.id.etIngredientAddTitle);

        btSave = (Button) view.findViewById(R.id.btSaveIngredient);

        String title = getArguments().getString("title", "New Ingredient");
        getDialog().setTitle(title);

        etIngredientTitle.requestFocus();

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    public void onTypeRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioProtein:
                if (checked) {

                }
                break;
            case R.id.radioVeggies:
                if (checked) {

                }
                break;
            case R.id.radioFruit:
                if (checked) {

                }
                break;
            case R.id.radioSpices:
                if (checked) {

                }
                break;
            case R.id.radioDairy:
                if (checked) {

                }
                break;
            default:

        }
    }
}
