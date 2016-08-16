package com.sonnytron.sortatech.pantryprep.Models.Common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attributes {

    @SerializedName("course")
    @Expose
    private List<String> course = new ArrayList<String>();
    @SerializedName("cuisine")
    @Expose
    private List<String> cuisine = new ArrayList<String>();

    /**
     *
     * @return
     *     The course
     */
    public List<String> getCourse() {
        return course;
    }

    /**
     *
     * @param course
     *     The course
     */
    public void setCourse(List<String> course) {
        this.course = course;
    }

    /**
     *
     * @return
     *     The cuisine
     */
    public List<String> getCuisine() {
        return cuisine;
    }

    /**
     *
     * @param cuisine
     *     The cuisine
     */
    public void setCuisine(List<String> cuisine) {
        this.cuisine = cuisine;
    }

}
