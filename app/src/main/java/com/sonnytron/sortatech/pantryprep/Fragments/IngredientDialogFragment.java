package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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
    private IngredientCallback mCallback;

    public IngredientDialogFragment() {

    }

    public interface IngredientCallback {
        void saveIngredient(Ingredient ingredient);
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

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIngredient();
            }
        });

        String title = getArguments().getString("title", "New Ingredient");
        getDialog().setTitle(title);

        etIngredientTitle.requestFocus();

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (IngredientCallback) context;
    }

    public void onTypeRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioProtein:
                if (checked) {
                    mIngredient.setType("protein");
                }
                break;
            case R.id.radioVeggies:
                if (checked) {
                    mIngredient.setType("veggies");
                }
                break;
            case R.id.radioFruit:
                if (checked) {
                    mIngredient.setType("fruit");
                }
                break;
            case R.id.radioSpices:
                if (checked) {
                    mIngredient.setType("spices");
                }
                break;
            case R.id.radioDairy:
                if (checked) {
                    mIngredient.setType("dairy");
                }
                break;
            default:

        }
    }

    private void saveIngredient() {
        Date mDate = new Date();
        mDate.setTime(SystemClock.currentThreadTimeMillis());
        mIngredient.setExpDate(mDate);
        if (ingredientValidated()) {
            mCallback.saveIngredient(mIngredient);
            getDialog().dismiss();
        } else {
            Toast.makeText(getActivity(), "Please fill all required fields!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ingredientValidated() {
        return mIngredient.getTitle() != null &&
                mIngredient.getTitle().length() > 0 &&
                mIngredient.getType() != null &&
                mIngredient.getType().length() > 0;
    }
}
