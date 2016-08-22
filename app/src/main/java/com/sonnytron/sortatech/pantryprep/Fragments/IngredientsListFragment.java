package com.sonnytron.sortatech.pantryprep.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonnyrodriguez on 8/17/16.
 */
public class IngredientsListFragment extends Fragment implements IngredientDialogFragment.IngredientCallback {
    private RecyclerView rvIngredients;
    private FloatingActionButton btAddIngredient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_fragment, container, false);

        rvIngredients = (RecyclerView) view.findViewById(R.id.rvIngredients);
        rvIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));

        btAddIngredient = (FloatingActionButton) view.findViewById(R.id.float_add_ing);
        btAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddIngredient();
            }
        });
        
        return view;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {

    }

    public void showAddIngredient() {
        FragmentManager fm = getFragmentManager();
        IngredientDialogFragment dialogFragment = IngredientDialogFragment.newInstance("New Ingredient");
        dialogFragment.show(fm, "ingredient_dialog_fragment");
    }
}
