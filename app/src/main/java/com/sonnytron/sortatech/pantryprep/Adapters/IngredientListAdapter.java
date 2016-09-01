package com.sonnytron.sortatech.pantryprep.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
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
        boolean onItemLongClick(int position, View view, Ingredient ingredient);
        void onItemClick(int position, View view);
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
            String daysRemaining = "";
            if (mIngredient.daysRemaining() > -1) {
                daysRemaining = mIngredient.daysRemaining() == 1 ? mIngredient.daysRemaining() + " day remaining" : mIngredient.daysRemaining() + " days remaining";
            } else if (mIngredient.daysRemaining() < 0) {
                daysRemaining = mIngredient.daysRemaining() == -1 ? "expired 1 day ago" : "expired " + mIngredient.daysRemaining() + " days ago";
                tvIngredientType.setTextColor(ContextCompat.getColor(mContext, R.color.PantryRed));
            }

            tvIngredientType.setText(daysRemaining);

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
    public void onBindViewHolder(final IngredientViewHolder holder, int position) {
        final Ingredient ingredient = mIngredients.get(position);
        holder.bindContext(mContext);
        holder.bindIngredient(ingredient);

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (mCallback != null) {
                        mIngredients.get(adapterPos);
                        mCallback.onItemLongClick(adapterPos, holder.view, ingredient);
                    }
                }
                return false;
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (mCallback != null) {
                        mCallback.onItemClick(adapterPos, holder.view);
                    }
                }
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
