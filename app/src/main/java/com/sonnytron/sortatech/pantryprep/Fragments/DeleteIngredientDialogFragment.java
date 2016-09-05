package com.sonnytron.sortatech.pantryprep.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

/**
 * Created by sonnyrodriguez on 8/28/16.
 */
public class DeleteIngredientDialogFragment extends DialogFragment {
    private Button btConfirm;
    private Button btCancel;
    private Ingredient mIngredient;

    public DeleteIngredientDialogFragment() {

    }

    public interface DeleteIngredientListener {
        void onIngredientDeleteConfirmed();
    }

    public static DeleteIngredientDialogFragment newInstance(Ingredient ingredient) {
        DeleteIngredientDialogFragment fragment = new DeleteIngredientDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putString("title", ingredient.getTitle());
        fragment.setArguments(arguments);
        return fragment;
    }

    public void setIngredient(Ingredient ingredient) {
        mIngredient = ingredient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delete_ingredient, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btConfirm = (Button) view.findViewById(R.id.btDeleteConfirm);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIngredient != null) {
                    //delete the ingredient from db
                    IngredientManager ingredientManager = IngredientManager.get(getActivity());
                    ingredientManager.deleteIngredient(mIngredient);
                    //callback to let us know deletion finished, and to refresh ui.
                    DeleteIngredientListener listener = (DeleteIngredientListener) getParentFragment();
                    listener.onIngredientDeleteConfirmed();
                    //listener.onIngredientDeleteConfirmed(true, mIngredient);
                    dismiss();
                }
            }
        });
        btCancel = (Button) view.findViewById(R.id.btDeleteCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIngredient != null) {
                    //DeleteIngredientListener listener = (DeleteIngredientListener) getActivity();
                    //listener.onIngredientDeleteConfirmed(false, mIngredient);
                    dismiss();
                }
            }
        });
    }
}
