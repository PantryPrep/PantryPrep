package com.sonnytron.sortatech.pantryprep.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


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
            AssetManager am = mContext.getApplicationContext().getAssets();

            Typeface poppinsFont = Typeface.createFromAsset(am, "fonts/Poppins-SemiBold.ttf");
            tvIngredientTitle.setTypeface(poppinsFont);

            String daysRemaining = "";

            if (mIngredient.daysRemaining() > -1) {
                daysRemaining = mIngredient.daysRemaining() == 1 ? mIngredient.daysRemaining() + " day remaining" : mIngredient.daysRemaining() + " days remaining";
            } else if (mIngredient.daysRemaining() < 0) {
                daysRemaining = mIngredient.daysRemaining() == -1 ? "expired 1 day ago" : "expired " + mIngredient.daysRemaining() + " days ago";
                tvIngredientType.setTextColor(ContextCompat.getColor(mContext, R.color.PantryRed));
            }

            Typeface sansFont = Typeface.createFromAsset(am, "fonts/PT_Sans-Web-Italic.ttf");

            tvIngredientType.setText(daysRemaining);
            tvIngredientType.setTypeface(sansFont);



            if (mIngredient.getType().equals("protein")) {
                Glide.with(mContext).load(R.mipmap.icon_protein).bitmapTransform(new CropCircleTransformation(mContext)).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("dairy")) {
                Glide.with(mContext).load(R.mipmap.icon_dairy).bitmapTransform(new CropCircleTransformation(mContext)).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("fruit")) {
                Glide.with(mContext).load(R.mipmap.icon_fruit).bitmapTransform(new CropCircleTransformation(mContext)).into(ivIngredientPhoto);
            } else if (mIngredient.getType().equals("veggies")) {
                Glide.with(mContext).load(R.mipmap.icon_veggies).bitmapTransform(new CropCircleTransformation(mContext)).into(ivIngredientPhoto);
            } else {
                Glide.with(mContext).load(R.mipmap.icon_spices).bitmapTransform(new CropCircleTransformation(mContext)).into(ivIngredientPhoto);
            }
        }

        public interface OnViewHolderListener {
            void onIngredientClick(View caller, int position);
            void onIngredientDelete(View caller, int position);
        }
    }


    public IngredientListAdapter(Context context, List<Ingredient> ingredients) {
        mContext = context;
        //mCallback = (ListAdapterCallback) mContext;
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
