
package com.sonnytron.sortatech.pantryprep.Models.Recipes;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sonnytron.sortatech.pantryprep.Models.Common.Attributes;
import com.sonnytron.sortatech.pantryprep.Models.Common.Attribution;
import com.sonnytron.sortatech.pantryprep.Models.Common.Flavors;


public class RecipeDetails {

    @SerializedName("yield")
    @Expose
    private String yield;
    @SerializedName("nutritionEstimates")
    @Expose
    private List<NutritionEstimate> nutritionEstimates = new ArrayList<NutritionEstimate>();
    @SerializedName("totalTime")
    @Expose
    private String totalTime;
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("source")
    @Expose
    private HostSource hostSource;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ingredientLines")
    @Expose
    private List<String> ingredientLines = new ArrayList<String>();
    @SerializedName("attribution")
    @Expose
    private Attribution attribution;
    @SerializedName("numberOfServings")
    @Expose
    private Integer numberOfServings;
    @SerializedName("totalTimeInSeconds")
    @Expose
    private Integer totalTimeInSeconds;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("flavors")
    @Expose
    private Flavors flavors;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    /**
     *
     * @return
     * The yield
     */
    public String getYield() {
        return yield;
    }

    /**
     *
     * @param yield
     * The yield
     */
    public void setYield(String yield) {
        this.yield = yield;
    }

    /**
     *
     * @return
     * The nutritionEstimates
     */
    public List<NutritionEstimate> getNutritionEstimates() {
        return nutritionEstimates;
    }

    /**
     *
     * @param nutritionEstimates
     * The nutritionEstimates
     */
    public void setNutritionEstimates(List<NutritionEstimate> nutritionEstimates) {
        this.nutritionEstimates = nutritionEstimates;
    }

    /**
     *
     * @return
     * The totalTime
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     *
     * @param totalTime
     * The totalTime
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     *
     * @return
     * The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The source
     */
    public HostSource getSource() {
        return hostSource;
    }

    public void setSource(HostSource inSource) {
        this.hostSource = inSource;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The ingredientLines
     */
    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    /**
     *
     * @param ingredientLines
     * The ingredientLines
     */
    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    /**
     *
     * @return
     * The attribution
     */
    public Attribution getAttribution() {
        return attribution;
    }

    /**
     *
     * @param attribution
     * The attribution
     */
    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    /**
     *
     * @return
     * The numberOfServings
     */
    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    /**
     *
     * @param numberOfServings
     * The numberOfServings
     */
    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    /**
     *
     * @return
     * The totalTimeInSeconds
     */
    public Integer getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    /**
     *
     * @param totalTimeInSeconds
     * The totalTimeInSeconds
     */
    public void setTotalTimeInSeconds(Integer totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    /**
     *
     * @return
     * The attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     * The attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return
     * The flavors
     */
    public Flavors getFlavors() {
        return flavors;
    }

    /**
     *
     * @param flavors
     * The flavors
     */
    public void setFlavors(Flavors flavors) {
        this.flavors = flavors;
    }

    /**
     *
     * @return
     * The rating
     */

    public Integer getRating() {
        return rating;
    }


    /**
     *
     * @param rating
     * The rating
     */

    public void setRating(Integer rating) {
        this.rating = rating;
    }


}
