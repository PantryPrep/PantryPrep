package com.sonnytron.sortatech.pantryprep.Models.Common;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Flavors {

    @SerializedName("salty")
    @Expose
    private Double salty;
    @SerializedName("sour")
    @Expose
    private Double sour;
    @SerializedName("sweet")
    @Expose
    private Double sweet;
    @SerializedName("bitter")
    @Expose
    private Double bitter;
    @SerializedName("meaty")
    @Expose
    private Double meaty;
    @SerializedName("piquant")
    @Expose
    private Double piquant;

    /**
     *
     * @return
     *     The salty
     */
    public Double getSalty() {
        return salty;
    }

    /**
     *
     * @param salty
     *     The salty
     */
    public void setSalty(Double salty) {
        this.salty = salty;
    }

    /**
     *
     * @return
     *     The sour
     */
    public Double getSour() {
        return sour;
    }

    /**
     *
     * @param sour
     *     The sour
     */
    public void setSour(Double sour) {
        this.sour = sour;
    }

    /**
     *
     * @return
     *     The sweet
     */
    public Double getSweet() {
        return sweet;
    }

    /**
     *
     * @param sweet
     *     The sweet
     */
    public void setSweet(Double sweet) {
        this.sweet = sweet;
    }

    /**
     *
     * @return
     *     The bitter
     */
    public Double getBitter() {
        return bitter;
    }

    /**
     *
     * @param bitter
     *     The bitter
     */
    public void setBitter(Double bitter) {
        this.bitter = bitter;
    }

    /**
     *
     * @return
     *     The meaty
     */
    public Double getMeaty() {
        return meaty;
    }

    /**
     *
     * @param meaty
     *     The meaty
     */
    public void setMeaty(Double meaty) {
        this.meaty = meaty;
    }

    /**
     *
     * @return
     *     The piquant
     */
    public Double getPiquant() {
        return piquant;
    }

    /**
     *
     * @param piquant
     *     The piquant
     */
    public void setPiquant(Double piquant) {
        this.piquant = piquant;
    }

}
