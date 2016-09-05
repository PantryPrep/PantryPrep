package com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonnytron.sortatech.pantryprep.Fragments.IngredientsListFragment;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.List;


public class IngredientsAllFragment extends IngredientsListFragment {

    public IngredientsAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void updateUI() {
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        List<Ingredient> ingredients = ingredientManager.getIngredients();

        addAll(ingredients);
        setSpinner(0);
    }

}
