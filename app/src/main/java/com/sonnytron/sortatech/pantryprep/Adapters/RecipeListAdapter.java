package com.sonnytron.sortatech.pantryprep.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sonnytron.sortatech.pantryprep.Activity.RecipeLookupActivity;
import com.sonnytron.sortatech.pantryprep.Models.Common.Attributes;
import com.sonnytron.sortatech.pantryprep.Models.Query.Match;
import com.sonnytron.sortatech.pantryprep.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Steve on 8/17/2016.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder>{

    private Context mContext;
    //list of recipes.  Large recipe image under "RecipeDetails",
    private List<Match> mRecipesMatches;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public OnViewHolderClickListener viewHolderClickListener;

        public ImageView ivRecipeImage;
        public TextView tvRecipeTitle;
        public RatingBar rbFoodRating;
        public TextView tvCourseType;
        public TextView tvCuisineType;

        //constructor
        public ViewHolder(View itemView, OnViewHolderClickListener listener) {
            super(itemView);

            ivRecipeImage = (ImageView) itemView.findViewById(R.id.ivRecipeImage);
            tvRecipeTitle = (TextView) itemView.findViewById(R.id.tvRecipeTitle);
            tvCourseType= (TextView) itemView.findViewById(R.id.tvCourse);
            tvCuisineType = (TextView) itemView.findViewById(R.id.tvCuisineType);
            rbFoodRating = (RatingBar) itemView.findViewById(R.id.rbFoodRating);

            if (listener != null) {
                viewHolderClickListener = listener;
                //note: change this to respond to click on the row. Might use the recycler view on click listener from codepath.
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

    public RecipeListAdapter(Context context, List<Match> recipeDetails) {
        mContext = context;
        mRecipesMatches = recipeDetails;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_list, parent, false);


        ViewHolder viewHolder = new ViewHolder(recipeView, new ViewHolder.OnViewHolderClickListener(){

            //do logic for item click on here.
            @Override
            public void onItemClick(View caller, int position) {
                Match clickedMatch = mRecipesMatches.get(position);
                //launch intent to inflate recipe lookup activity.
                Intent i = new Intent(getContext(), RecipeLookupActivity.class);
                //need recipe ID to look up.
                i.putExtra("recipe_id", clickedMatch.getId());
                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.ViewHolder holder, int position) {
        Match recipeMatch = mRecipesMatches.get(position);

        TextView recipeTitle = holder.tvRecipeTitle;
        TextView courseType = holder.tvCourseType;
        TextView cuisineType = holder.tvCuisineType;
        ImageView recipePicture = holder.ivRecipeImage;
        RatingBar ratingBar = holder.rbFoodRating;

        Attributes attributes = recipeMatch.getAttributes();
        List<String> courses = attributes.getCourse();
        List<String> cuisines = attributes.getCuisine();

        String finalCourse = "";
        String finalCuisine = "";

        //fix if course is empty but cuisine exists
        if (courses.size() == 0){
            courses = cuisines;
        }

        for(String i:courses){
            finalCourse += i + ", ";
        }
        for(String i:cuisines){
            finalCuisine += i + ", ";
        }
        finalCourse = finalCourse.replaceAll(", $", "");
        finalCuisine = finalCuisine.replaceAll(", $", "");



        courseType.setText(finalCourse);
        cuisineType.setText(finalCuisine);




        ratingBar.setRating(recipeMatch.getRating());
        List<String> smallImageUrl = recipeMatch.getSmallImageUrls();

        //set data in recycler view.
        recipeTitle.setText(recipeMatch.getRecipeName());

        //if we have an image, print it.
        if (smallImageUrl.size() > 0) {
            Glide.with(getContext())
                    .load(smallImageUrl.get(0))
                    .centerCrop()
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .placeholder(R.mipmap.ic_food_placeholder)
                    .into(recipePicture);
        }
        else{
            Glide.with(getContext())
                    .load(R.mipmap.ic_food_placeholder)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .centerCrop()
                    .into(recipePicture);
        }
    }

    @Override
    public int getItemCount() {
        return mRecipesMatches.size();
    }


    //Helper Classes -----------------------------------------------------

    private Context getContext() {
        return mContext;
    }

    //wipe data.
    public void clearData() {
        int size = this.mRecipesMatches.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mRecipesMatches.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
