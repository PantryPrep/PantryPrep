package com.sonnytron.sortatech.pantryprep.Models.Query;

import android.location.Criteria;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sonnytron.sortatech.pantryprep.Models.Common.Attribution;


public class RecipeQuery {

    @SerializedName("attribution")
    @Expose
    private Attribution attribution;
    @SerializedName("totalMatchCount")
    @Expose
    private Integer totalMatchCount;
    @SerializedName("facetCounts")
    @Expose
    private FacetCounts facetCounts;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = new ArrayList<Match>();
    @SerializedName("criteria")
    @Expose
    private Criteria criteria;

    /**
     *
     * @return
     *     The attribution
     */
    public Attribution getAttribution() {
        return attribution;
    }

    /**
     *
     * @param attribution
     *     The attribution
     */
    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    /**
     *
     * @return
     *     The totalMatchCount
     */
    public Integer getTotalMatchCount() {
        return totalMatchCount;
    }

    /**
     *
     * @param totalMatchCount
     *     The totalMatchCount
     */
    public void setTotalMatchCount(Integer totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }

    /**
     *
     * @return
     *     The facetCounts
     */
    public FacetCounts getFacetCounts() {
        return facetCounts;
    }

    /**
     *
     * @param facetCounts
     *     The facetCounts
     */
    public void setFacetCounts(FacetCounts facetCounts) {
        this.facetCounts = facetCounts;
    }

    /**
     *
     * @return
     *     The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     *
     * @param matches
     *     The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     *
     * @return
     *     The criteria
     */
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     *
     * @param criteria
     *     The criteria
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

}
