package com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters;


import android.os.Bundle;

import com.sonnytron.sortatech.pantryprep.Fragments.IngredientsListFragment;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.List;


public class ProteinFragment extends IngredientsListFragment {

    public ProteinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void updateUI() {
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        List<Ingredient> ingredients = ingredientManager.getIngredientsProtein();

        addAll(ingredients);
        setSpinner(1);
    }

}
