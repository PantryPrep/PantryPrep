package com.sonnytron.sortatech.pantryprep.Models.Query;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Criteria {

    @SerializedName("maxResults")
    @Expose
    private Integer maxResults;
    @SerializedName("excludedIngredients")
    @Expose
    private List<String> excludedIngredients = new ArrayList<String>();
    @SerializedName("excludedAttributes")
    @Expose
    private List<Object> excludedAttributes = new ArrayList<Object>();
    @SerializedName("allowedIngredients")
    @Expose
    private List<String> allowedIngredients = new ArrayList<String>();
    @SerializedName("attributeRanges")
    @Expose
    private AttributeRanges attributeRanges;
    @SerializedName("nutritionRestrictions")
    @Expose
    private NutritionRestrictions nutritionRestrictions;
    @SerializedName("allowedDiets")
    @Expose
    private List<String> allowedDiets = new ArrayList<String>();
    @SerializedName("resultsToSkip")
    @Expose
    private Integer resultsToSkip;
    @SerializedName("requirePictures")
    @Expose
    private Boolean requirePictures;
    @SerializedName("facetFields")
    @Expose
    private List<Object> facetFields = new ArrayList<Object>();
    @SerializedName("terms")
    @Expose
    private List<String> terms = new ArrayList<String>();
    @SerializedName("allowedAttributes")
    @Expose
    private List<String> allowedAttributes = new ArrayList<String>();

    /**
     *
     * @return
     *     The maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     *
     * @param maxResults
     *     The maxResults
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     *
     * @return
     *     The excludedIngredients
     */
    public List<String> getExcludedIngredients() {
        return excludedIngredients;
    }

    /**
     *
     * @param excludedIngredients
     *     The excludedIngredients
     */
    public void setExcludedIngredients(List<String> excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }

    /**
     *
     * @return
     *     The excludedAttributes
     */
    public List<Object> getExcludedAttributes() {
        return excludedAttributes;
    }

    /**
     *
     * @param excludedAttributes
     *     The excludedAttributes
     */
    public void setExcludedAttributes(List<Object> excludedAttributes) {
        this.excludedAttributes = excludedAttributes;
    }

    /**
     *
     * @return
     *     The allowedIngredients
     */
    public List<String> getAllowedIngredients() {
        return allowedIngredients;
    }

    /**
     *
     * @param allowedIngredients
     *     The allowedIngredients
     */
    public void setAllowedIngredients(List<String> allowedIngredients) {
        this.allowedIngredients = allowedIngredients;
    }

    /**
     *
     * @return
     *     The attributeRanges
     */
    public AttributeRanges getAttributeRanges() {
        return attributeRanges;
    }

    /**
     *
     * @param attributeRanges
     *     The attributeRanges
     */
    public void setAttributeRanges(AttributeRanges attributeRanges) {
        this.attributeRanges = attributeRanges;
    }

    /**
     *
     * @return
     *     The nutritionRestrictions
     */
    public NutritionRestrictions getNutritionRestrictions() {
        return nutritionRestrictions;
    }

    /**
     *
     * @param nutritionRestrictions
     *     The nutritionRestrictions
     */
    public void setNutritionRestrictions(NutritionRestrictions nutritionRestrictions) {
        this.nutritionRestrictions = nutritionRestrictions;
    }

    /**
     *
     * @return
     *     The allowedDiets
     */
    public List<String> getAllowedDiets() {
        return allowedDiets;
    }

    /**
     *
     * @param allowedDiets
     *     The allowedDiets
     */
    public void setAllowedDiets(List<String> allowedDiets) {
        this.allowedDiets = allowedDiets;
    }

    /**
     *
     * @return
     *     The resultsToSkip
     */
    public Integer getResultsToSkip() {
        return resultsToSkip;
    }

    /**
     *
     * @param resultsToSkip
     *     The resultsToSkip
     */
    public void setResultsToSkip(Integer resultsToSkip) {
        this.resultsToSkip = resultsToSkip;
    }

    /**
     *
     * @return
     *     The requirePictures
     */
    public Boolean getRequirePictures() {
        return requirePictures;
    }

    /**
     *
     * @param requirePictures
     *     The requirePictures
     */
    public void setRequirePictures(Boolean requirePictures) {
        this.requirePictures = requirePictures;
    }

    /**
     *
     * @return
     *     The facetFields
     */
    public List<Object> getFacetFields() {
        return facetFields;
    }

    /**
     *
     * @param facetFields
     *     The facetFields
     */
    public void setFacetFields(List<Object> facetFields) {
        this.facetFields = facetFields;
    }

    /**
     *
     * @return
     *     The terms
     */
    public List<String> getTerms() {
        return terms;
    }

    /**
     *
     * @param terms
     *     The terms
     */
    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    /**
     *
     * @return
     *     The allowedAttributes
     */
    public List<String> getAllowedAttributes() {
        return allowedAttributes;
    }

    /**
     *
     * @param allowedAttributes
     *     The allowedAttributes
     */
    public void setAllowedAttributes(List<String> allowedAttributes) {
        this.allowedAttributes = allowedAttributes;
    }

}
