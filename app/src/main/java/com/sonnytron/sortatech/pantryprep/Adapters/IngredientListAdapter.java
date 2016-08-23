package com.sonnytron.sortatech.pantryprep.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.List;

/**
 * Created by sonnyrodriguez on 8/22/16.
 */
public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Ingredient> mIngredients;

    public static class IngredientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnViewHolderListener mViewHolderListener;

        private TextView tvIngredientTitle;
        private TextView tvIngredientType;
        private ImageView ivIngredientPhoto;
        private Ingredient mIngredient;

        public IngredientViewHolder(View itemView, OnViewHolderListener viewHolderListener) {
            super(itemView);
            tvIngredientTitle = (TextView) itemView.findViewById(R.id.tvIngredientTitle);
            tvIngredientType = (TextView) itemView.findViewById(R.id.tvIngredientType);
            ivIngredientPhoto = (ImageView) itemView.findViewById(R.id.ivIngredient);
            mViewHolderListener = viewHolderListener;
        }

        public void bindIngredient(Ingredient ingredient) {
            mIngredient = ingredient;
            updateLayout();
        }

        private void updateLayout() {
            tvIngredientTitle.setText(mIngredient.getTitle());
            tvIngredientType.setText(mIngredient.getType());
            if (mIngredient.getType() == "protein") {
                ivIngredientPhoto.setImageResource(R.drawable.ic_protein);
            } else if (mIngredient.getType() == "dairy") {
                ivIngredientPhoto.setImageResource(R.drawable.ic_dairy);
            } else if (mIngredient.getType() == "fruit") {
                ivIngredientPhoto.setImageResource(R.drawable.ic_fruit);
            } else if (mIngredient.getType() == "veggies") {
                ivIngredientPhoto.setImageResource(R.drawable.ic_veggies);
            } else {
                ivIngredientPhoto.setImageResource(R.drawable.ic_spices);
            }
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            mViewHolderListener.onIngredientClick(view, pos);
        }

        public interface OnViewHolderListener {
            void onIngredientClick(View caller, int position);
        }
    }

    public IngredientListAdapter(Context context, List<Ingredient> ingredients) {
        mContext = context;
        mIngredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientViewHolder(view, new IngredientViewHolder.OnViewHolderListener() {
            @Override
            public void onIngredientClick(View caller, int position) {

            }
        });
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        holder.bindIngredient(ingredient);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    private Context getContext() {
        return mContext;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }
}