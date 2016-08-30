package com.sonnytron.sortatech.pantryprep.Models.Query;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sonnytron.sortatech.pantryprep.Models.Common.Attributes;
import com.sonnytron.sortatech.pantryprep.Models.Common.Flavors;


public class Match {

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("flavors")
    @Expose
    private Flavors flavors;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("smallImageUrls")
    @Expose
    private List<String> smallImageUrls = new ArrayList<>();
    @SerializedName("sourceDisplayName")
    @Expose
    private String sourceDisplayName;
    @SerializedName("totalTimeInSeconds")
    @Expose
    private Integer totalTimeInSeconds;
    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients = new ArrayList<>();
    @SerializedName("recipeName")
    @Expose
    private String recipeName;

    /**
     *
     * @return
     *     The attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return
     *     The flavors
     */
    public Flavors getFlavors() {
        return flavors;
    }

    /**
     *
     * @param flavors
     *     The flavors
     */
    public void setFlavors(Flavors flavors) {
        this.flavors = flavors;
    }

    /**
     *
     * @return
     *     The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     *     The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The smallImageUrls
     */
    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }

    /**
     *
     * @param smallImageUrls
     *     The smallImageUrls
     */
    public void setSmallImageUrls(List<String> smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    /**
     *
     * @return
     *     The sourceDisplayName
     */
    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    /**
     *
     * @param sourceDisplayName
     *     The sourceDisplayName
     */
    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    /**
     *
     * @return
     *     The totalTimeInSeconds
     */
    public Integer getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    /**
     *
     * @param totalTimeInSeconds
     *     The totalTimeInSeconds
     */
    public void setTotalTimeInSeconds(Integer totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    /**
     *
     * @return
     *     The ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     *     The ingredients
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return
     *     The recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     *
     * @param recipeName
     *     The recipeName
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

}
