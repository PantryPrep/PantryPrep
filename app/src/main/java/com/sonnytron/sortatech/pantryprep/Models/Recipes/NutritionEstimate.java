
package com.sonnytron.sortatech.pantryprep.Models.Recipes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NutritionEstimate {

    @SerializedName("attribute")
    @Expose
    private String attribute;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("unit")
    @Expose
    private Unit unit;

    /**
     *
     * @return
     *     The attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     *
     * @param attribute
     *     The attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value
     *     The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     *
     * @return
     *     The unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     *     The unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

}
