package com.sonnytron.sortatech.pantryprep.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Models.Recipes.RecipeDetail;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.List;

/**
 * Created by Steve on 8/17/2016.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder>{

    private Context mContext;
    //list of recipes.  Large recipe image under "RecipeDetail",
    private List<RecipeDetail> mRecipes;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public OnViewHolderClickListener viewHolderClickListener;

        public ImageView ivRecipeImage;
        public TextView tvRecipeTitle;

        //constructor
        public ViewHolder(View itemView, OnViewHolderClickListener listener) {
            super(itemView);

            ivRecipeImage = (ImageView) itemView.findViewById(R.id.ivRecipeImage);
            tvRecipeTitle = (TextView) itemView.findViewById(R.id.tvRecipeTitle);

            if (listener != null) {
                viewHolderClickListener = listener;
                ivRecipeImage.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            int itemPos = getAdapterPosition();
            viewHolderClickListener.onItemClick(view, itemPos);
        }

        //interface for utilizing item click.
        public interface OnViewHolderClickListener {
            void onItemClick(View caller, int position);
        }
    }

    public RecipeListAdapter(Context context, List<RecipeDetail> recipeDetails) {
        mContext = context;
        mRecipes = recipeDetails;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_list, parent, false);


        ViewHolder viewHolder = new ViewHolder(recipeView, new ViewHolder.OnViewHolderClickListener(){

            @Override
            public void onItemClick(View caller, int position) {
                //fill out data on activity.
                Log.d("onItemClick: ", "recipe item clicked");
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.ViewHolder holder, int position) {
        RecipeDetail recipe = mRecipes.get(position);

        TextView recipeTitle = holder.tvRecipeTitle;
        ImageView recipePicture = holder.ivRecipeImage;

        List<com.sonnytron.sortatech.pantryprep.Models.Recipes.Image> imageList = recipe.getImages();

        //set data in recycler view.
        recipeTitle.setText(recipe.getName());
        if (imageList.size() > 0) {

            Glide.with(getContext())
                    .load(imageList.get(0).getHostedLargeUrl())
                    //.placeholder()
                    .into(recipePicture);
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    //Helper Classes -----------------------------------------------------

    private Context getContext() {
        return mContext;
    }

    //wipe data.
    public void clearData() {
        int size = this.mRecipes.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mRecipes.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
