package com.sonnytron.sortatech.pantryprep.Models.Query;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NutritionRestrictions {

    @SerializedName("FAT")
    @Expose
    private FAT fAT;

    /**
     *
     * @return
     *     The fAT
     */
    public FAT getFAT() {
        return fAT;
    }

    /**
     *
     * @param fAT
     *     The FAT
     */
    public void setFAT(FAT fAT) {
        this.fAT = fAT;
    }

}
