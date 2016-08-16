package com.sonnytron.sortatech.pantryprep.Models.Query;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FlavorPiquant {

    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("max")
    @Expose
    private Integer max;

    /**
     *
     * @return
     *     The min
     */
    public Double getMin() {
        return min;
    }

    /**
     *
     * @param min
     *     The min
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     *
     * @return
     *     The max
     */
    public Integer getMax() {
        return max;
    }

    /**
     *
     * @param max
     *     The max
     */
    public void setMax(Integer max) {
        this.max = max;
    }

}
