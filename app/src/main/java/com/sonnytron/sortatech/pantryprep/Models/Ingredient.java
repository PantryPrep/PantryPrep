package com.sonnytron.sortatech.pantryprep.Models;

import java.util.UUID;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class Ingredient {
    private String title;
    private UUID mId;

    public Ingredient(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return mId;
    }
}
