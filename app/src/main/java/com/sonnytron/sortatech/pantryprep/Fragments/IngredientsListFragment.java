package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Adapters.IngredientListAdapter;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sonnyrodriguez on 8/17/16.
 */
public class IngredientsListFragment extends Fragment implements IngredientDialogFragment.onAddFinishedListener{

    public static final String ARG_PAGE = "ARG_PAGE";

    private RecyclerView rvIngredients;
    private FloatingActionButton btAddIngredient;
    private IngredientListAdapter mAdapter;

    private int mPage;

    public static IngredientsListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        IngredientsListFragment fragment = new IngredientsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

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

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void showAddIngredient() {
        //FragmentManager fm = getFragmentManager();
        IngredientDialogFragment dialogFragment = IngredientDialogFragment.newInstance("New Ingredient");
        dialogFragment.show(getChildFragmentManager(), "ingredient_dialog_fragment");
    }

    public void addAll(List<Ingredient> ingredients) {
        if (mAdapter == null) {
            mAdapter = new IngredientListAdapter(getActivity(), ingredients);
            rvIngredients.setAdapter(mAdapter);
        } else {
            mAdapter.setIngredients(ingredients);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void updateUI() {
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        List<Ingredient> ingredients = ingredientManager.getIngredients();

        addAll(ingredients);
    }

    @Override
    public void onFilterFinish() {
        updateUI();
    }
}
