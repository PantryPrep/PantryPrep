package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Activity.HomeActivity;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


//note: this is a child fragment of RecipeListFragment
public class IngredientFilterFragment extends DialogFragment {

    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.lvFilterRecipeList)
    ListView lvFilterRecipeList;

    ArrayAdapter<String> listViewAdapter;
    ArrayList<String> items;
    ArrayList<String> filteredIngredients;

    // Required empty public constructor
    public IngredientFilterFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filteredIngredients = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_ingredient_filter, container, false);
        view.setBackgroundColor(Color.WHITE);
        ButterKnife.bind(this, view);
        initButtonListeners();

        //init array adapter
        items = new ArrayList<>();
        listViewAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_filter_ingredients, items);
        lvFilterRecipeList.setAdapter(listViewAdapter);

        populateList();

        return view;
    }


    private void populateList() {
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        List<Ingredient> ingredients = ingredientManager.getIngredientsNoSpice(); //NOTE: NEED TO EXCLUDE SPICES!!!!!


        Bundle bundle = this.getArguments();
        ArrayList<String> expiringIngredientList = bundle.getStringArrayList("selected_ingredients");

        for (int i = 0; i < ingredients.size(); i++) {
            items.add(ingredients.get(i).getTitle());
        }
        listViewAdapter.notifyDataSetChanged();


        //set item checked... break out as soon as we find the item.
        for (int i = 0; i < expiringIngredientList.size(); i++) {
            for (int j = 0; j < lvFilterRecipeList.getCount(); j++) {
                String listItem =lvFilterRecipeList.getItemAtPosition(j).toString();
                String expiringIngredients = expiringIngredientList.get(i);

                if (listItem.equals(expiringIngredients)) {
                    lvFilterRecipeList.setItemChecked(j, true);
                    break;
                }
            }
        }
    }

    //initialize our button listeners.  Cancel will pop the fragment and save should pass data back..
    private void initButtonListeners() {
        //cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFilterFinishedListener listener = (onFilterFinishedListener) getParentFragment();
                listener.onFilterFinish(filteredIngredients, true);
                dismiss();
            }
        });

        //save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lvFilterRecipeList.getCheckedItemCount() > 5) {
                    Toast.makeText(getActivity(), "Please limit selection to five items or less", Toast.LENGTH_SHORT).show();
                }
                //loop through the list view and return checked item.
                else {
                    //set listener to parent fragment.
                    onFilterFinishedListener listener = (onFilterFinishedListener) getParentFragment();
                    SparseBooleanArray checked = lvFilterRecipeList.getCheckedItemPositions();
                    for (int i = 0; i < lvFilterRecipeList.getAdapter().getCount(); i ++){
                        if (checked.get(i)){
                            filteredIngredients.add(lvFilterRecipeList.getItemAtPosition(i).toString());
                        }
                    }
                    //returns our ingredients to the previous fragment so we can use it.
                    listener.onFilterFinish(filteredIngredients, false);
                    dismiss();
                }
            }
        });
    }

    public interface onFilterFinishedListener {
        void onFilterFinish(ArrayList<String> returnedList, Boolean cancel);
    }
}
