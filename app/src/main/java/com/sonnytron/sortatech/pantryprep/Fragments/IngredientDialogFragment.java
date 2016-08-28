package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.Calendar;
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
    private RadioGroup mGroup;

    public interface IngredientCallback {
        public void saveIngredient(Ingredient ingredient);
    }

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
        Calendar calendar = Calendar.getInstance();
        Date mDate = new Date();
        mDate = calendar.getTime();

        mIngredient.setExpDate(mDate);
        etIngredientTitle = (EditText) view.findViewById(R.id.etIngredientAddTitle);

        btSave = (Button) view.findViewById(R.id.btSaveIngredient);

        mGroup = (RadioGroup) view.findViewById(R.id.radioGroupType);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index) {
                    case 0:
                        mIngredient.setType("protein");
                        break;
                    case 1:
                        mIngredient.setType("veggies");
                        break;
                    case 2:
                        mIngredient.setType("dairy");
                        break;
                    case 3:
                        mIngredient.setType("fruit");
                        break;
                    case 4:
                        mIngredient.setType("spices");
                        break;
                    default:
                        mIngredient.setType("veggies");
                        break;
                }
                mIngredient.dateFromType();
            }
        });

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

    private void saveIngredient() {

        String title = etIngredientTitle.getText().toString();
        if (title != null && title.length() > 0) {
            mIngredient.setTitle(title);
        }
        if (ingredientValidated()) {
            mCallback.saveIngredient(mIngredient);
            getDialog().dismiss();
        } else {
            Toast.makeText(getActivity(), "Please fill all required fields!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (IngredientCallback) context;
    }

    private boolean ingredientValidated() {
        return mIngredient.getTitle() != null &&
                mIngredient.getTitle().length() > 0 &&
                mIngredient.getType() != null &&
                mIngredient.getType().length() > 0;
    }
}
