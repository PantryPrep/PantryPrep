
package com.sonnytron.sortatech.pantryprep.Models.Recipes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Source {

    @SerializedName("sourceDisplayName")
    @Expose
    private String sourceDisplayName;
    @SerializedName("sourceSiteUrl")
    @Expose
    private String sourceSiteUrl;
    @SerializedName("sourceRecipeUrl")
    @Expose
    private String sourceRecipeUrl;

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
     *     The sourceSiteUrl
     */
    public String getSourceSiteUrl() {
        return sourceSiteUrl;
    }

    /**
     *
     * @param sourceSiteUrl
     *     The sourceSiteUrl
     */
    public void setSourceSiteUrl(String sourceSiteUrl) {
        this.sourceSiteUrl = sourceSiteUrl;
    }

    /**
     *
     * @return
     *     The sourceRecipeUrl
     */
    public String getSourceRecipeUrl() {
        return sourceRecipeUrl;
    }

    /**
     *
     * @param sourceRecipeUrl
     *     The sourceRecipeUrl
     */
    public void setSourceRecipeUrl(String sourceRecipeUrl) {
        this.sourceRecipeUrl = sourceRecipeUrl;
    }

}
