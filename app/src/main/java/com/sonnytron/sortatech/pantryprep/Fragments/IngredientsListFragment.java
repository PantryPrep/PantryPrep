package com.sonnytron.sortatech.pantryprep.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Adapters.IngredientListAdapter;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.DairyFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.FruitFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.IngredientsAllFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.ProteinFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.SpicesFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.VeggieFragment;
import com.sonnytron.sortatech.pantryprep.Helpers.ItemClickSupport;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonnyrodriguez on 8/17/16.
 */
public abstract class IngredientsListFragment extends Fragment implements IngredientDialogFragment.onAddFinishedListener, DeleteIngredientDialogFragment.DeleteIngredientListener {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.rvIngredients)
    RecyclerView rvIngredients;
    @BindView(R.id.fabAddIng)
    FloatingActionButton fabAddIng;
    @BindView(R.id.spFilterItems)
    Spinner spFilterItems;

    private IngredientListAdapter mAdapter;

    private int mPage;
    private boolean isInitial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_fragment, container, false);
        ButterKnife.bind(this, view);
        isInitial = true;

        rvIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initialize elements
        initializeFABAddIng();
        initializeOrderSpinner();

        //add ingredients
        updateUI();

        //item long click support
        initItemLongClick();
        initItemClick();

        return view;
    }

    //initializers--------------------------------
    private void initItemLongClick() {
        ItemClickSupport.addTo(rvIngredients).setOnItemLongClickListener(
                new ItemClickSupport.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                        //grab list of ingredients.
                        IngredientManager ingredientManager = IngredientManager.get(getActivity());
                        List<Ingredient> ingredients = ingredientManager.getIngredients();


                        Ingredient ingredient = ingredients.get(position);
                        showDeleteIngredientAlert(ingredient);
                        return false;
                    }
                }
        );
    }

    //poc item click support.
    private void initItemClick() {
        ItemClickSupport.addTo(rvIngredients).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {

                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.d("onItemClicked: ", "short click");
                    }
                }
        );
    }

    private void initializeFABAddIng() {
        fabAddIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddIngredient();
            }
        });
    }

    public void initializeOrderSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.filter_list_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spFilterItems.setAdapter(adapter);
        //spFilterItems.setSelection(curPos);
        spFilterItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                //Log.d("onItemSelected: ", "called");
                //String item = adapterView.getItemAtPosition(pos).toString();
                if (!isInitial) {
                    launchFragment(pos);
                }
                else {
                    isInitial = false;
                }

                //set text color
                TextView tmpView = (TextView) spFilterItems.getSelectedView().findViewById(android.R.id.text1);
                tmpView.setTextColor(ContextCompat.getColor(getContext(), R.color.PantryWhite));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void setSpinner(int index){
        spFilterItems.setSelection(index);
    }

    private void launchFragment(int pos) {
        Fragment fragment = null;
        Class fragmentClass;

        //launch various filter fragments.
        switch (pos) {
            case 0:
                fragmentClass = IngredientsAllFragment.class;
                break;
            case 1:
                fragmentClass = ProteinFragment.class;
                break;
            case 2:
                fragmentClass = DairyFragment.class;
                break;
            case 3:
                fragmentClass = FruitFragment.class;
                break;
            case 4:
                fragmentClass = VeggieFragment.class;
                break;
            case 5:
                fragmentClass = SpicesFragment.class;
                break;
            default:
                fragmentClass = IngredientsAllFragment.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.frame_content, fragment);
        ft.commit();


    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void showAddIngredient() {
        IngredientDialogFragment dialogFragment = IngredientDialogFragment.newInstance("New Ingredient");
        dialogFragment.show(getChildFragmentManager(), "ingredient_dialog_fragment");
    }

    public void addAll(List<Ingredient> ingredients) {
        if (mAdapter == null) {
            mAdapter = new IngredientListAdapter(getContext(), ingredients);
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
    public void onFilterFinish(Ingredient ingredient) {
        //write to DB and refresh UI
        IngredientManager ingredientManager = IngredientManager.get(getActivity());
        ingredientManager.addIngredient(ingredient);
        updateUI();
    }


    @Override
    public void onIngredientDeleteConfirmed() {
        updateUI();
    }

    //long click helper
    public void showDeleteIngredientAlert(Ingredient ingredient) {
        FragmentManager fm = getChildFragmentManager();
        DeleteIngredientDialogFragment deleteFrag = DeleteIngredientDialogFragment.newInstance(ingredient);
        deleteFrag.setIngredient(ingredient);
        deleteFrag.show(fm, "fragment_alert");
    }
}
