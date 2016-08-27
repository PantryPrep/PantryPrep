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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sonnyrodriguez on 8/22/16.
 */
public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Ingredient> mIngredients;
    private ListAdapterCallback mCallback;

    public interface ListAdapterCallback {
        void ingredientFragmentRequest(Ingredient ingredient);
        void ingredientDeleteRequest(Ingredient ingredient);
    }

    public interface OnLongListener {
        public boolean onItemLongClick(int position);
    }

    public interface OnListener {
        public void onItemClick(int position);
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        public OnViewHolderListener mViewHolderListener;

        private TextView tvIngredientTitle;
        private TextView tvIngredientType;
        private ImageView ivIngredientPhoto;
        private Ingredient mIngredient;
        private Context mContext;
        public View view;

        public IngredientViewHolder(View itemView, OnViewHolderListener viewHolderListener) {
            super(itemView);
            this.view = itemView;
            tvIngredientTitle = (TextView) itemView.findViewById(R.id.tvIngredientTitle);
            tvIngredientType = (TextView) itemView.findViewById(R.id.tvIngredientType);
            ivIngredientPhoto = (ImageView) itemView.findViewById(R.id.ivIngredient);
            mViewHolderListener = viewHolderListener;
        }

        public void bindContext(Context context) {
            if (mContext == null) {
                mContext = context;
            }
        }

        public void bindIngredient(Ingredient ingredient) {
            mIngredient = ingredient;
            updateLayout();
        }

        private void updateLayout() {
            tvIngredientTitle.setText(mIngredient.getTitle());
            tvIngredientType.setText(mIngredient.getType());

            if (mIngredient.getType().equals("protein")) {
                Picasso.with(mContext).load(R.drawable.ic_protein).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("dairy")) {
                Picasso.with(mContext).load(R.drawable.ic_dairy).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("fruit")) {
                Picasso.with(mContext).load(R.drawable.ic_fruit).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("veggies")) {
                Picasso.with(mContext).load(R.drawable.ic_veggies).into(ivIngredientPhoto);
            } else {
                Picasso.with(mContext).load(R.drawable.ic_spices).into(ivIngredientPhoto);
            }
        }

        public interface OnViewHolderListener {
            void onIngredientClick(View caller, int position);
            void onIngredientDelete(View caller, int position);
        }
    }

    public IngredientListAdapter(Context context, List<Ingredient> ingredients) {
        mContext = context;
        mCallback = (ListAdapterCallback) mContext;
        mIngredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientViewHolder(view, new IngredientViewHolder.OnViewHolderListener() {
            @Override
            public void onIngredientClick(View caller, int position) {
                mCallback.ingredientFragmentRequest(mIngredients.get(position));
            }

            @Override
            public void onIngredientDelete(View caller, int position) {
                mCallback.ingredientDeleteRequest(mIngredients.get(position));
            }
        });
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        holder.bindContext(mContext);
        holder.bindIngredient(ingredient);

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return true;
            }
        });

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
