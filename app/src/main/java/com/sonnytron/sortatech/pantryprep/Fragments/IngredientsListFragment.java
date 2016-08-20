package com.sonnytron.sortatech.pantryprep.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonnytron.sortatech.pantryprep.R;

/**
 * Created by sonnyrodriguez on 8/17/16.
 */
public class IngredientsListFragment extends Fragment {
    private RecyclerView rvIngredients;

    private FloatingActionButton btAddIngredient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_fragment, container, false);

        rvIngredients = (RecyclerView) view.findViewById(R.id.rvIngredients);
        rvIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));

        btAddIngredient = (FloatingActionButton) view.findViewById(R.id.float_add_ing);

        return view;
    }
}
