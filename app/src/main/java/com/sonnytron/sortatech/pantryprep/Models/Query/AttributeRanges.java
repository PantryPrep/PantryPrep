package com.sonnytron.sortatech.pantryprep.Models.Query;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AttributeRanges {

    @SerializedName("flavor-piquant")
    @Expose
    private FlavorPiquant flavorPiquant;

    /**
     *
     * @return
     *     The flavorPiquant
     */
    public FlavorPiquant getFlavorPiquant() {
        return flavorPiquant;
    }

    /**
     *
     * @param flavorPiquant
     *     The flavor-piquant
     */
    public void setFlavorPiquant(FlavorPiquant flavorPiquant) {
        this.flavorPiquant = flavorPiquant;
    }

}
