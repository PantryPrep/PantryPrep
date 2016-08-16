
package com.sonnytron.sortatech.pantryprep.Models.Recipes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Unit {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("plural")
    @Expose
    private String plural;
    @SerializedName("pluralAbbreviation")
    @Expose
    private String pluralAbbreviation;
    @SerializedName("decimal")
    @Expose
    private Boolean decimal;

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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     *
     * @param abbreviation
     *     The abbreviation
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     *
     * @return
     *     The plural
     */
    public String getPlural() {
        return plural;
    }

    /**
     *
     * @param plural
     *     The plural
     */
    public void setPlural(String plural) {
        this.plural = plural;
    }

    /**
     *
     * @return
     *     The pluralAbbreviation
     */
    public String getPluralAbbreviation() {
        return pluralAbbreviation;
    }

    /**
     *
     * @param pluralAbbreviation
     *     The pluralAbbreviation
     */
    public void setPluralAbbreviation(String pluralAbbreviation) {
        this.pluralAbbreviation = pluralAbbreviation;
    }

    /**
     *
     * @return
     *     The decimal
     */
    public Boolean getDecimal() {
        return decimal;
    }

    /**
     *
     * @param decimal
     *     The decimal
     */
    public void setDecimal(Boolean decimal) {
        this.decimal = decimal;
    }

}
