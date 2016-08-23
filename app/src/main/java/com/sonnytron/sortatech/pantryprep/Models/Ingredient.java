package com.sonnytron.sortatech.pantryprep.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class Ingredient implements Parcelable {
    private String title;
    private UUID mId;
    private String stockPhoto;
    private String type;
    private long expLong;
    private Date expDate;

    public Ingredient() {
        mId = UUID.randomUUID();
    }

    public Ingredient(UUID id) {
        mId = id;
    }

    public static List<String> types() {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("protein");
        strs.add("veggies");
        strs.add("fruit");
        strs.add("spices");
        strs.add("dairy");
        return strs;
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

    public void setId(UUID id) {
        mId = id;
    }

    public String getStockPhoto() {
        return stockPhoto;
    }

    public void setStockPhoto(String stockPhoto) {
        this.stockPhoto = stockPhoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getExpLong() {
        if (expDate != null) {
            expLong = expDate.getTime();
        }
        return expLong;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setDateLong(long dateLong) {
        Date date = new Date();
        date.setTime(dateLong);
        expDate = date;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeSerializable(this.mId);
        dest.writeString(this.stockPhoto);
        dest.writeString(this.type);
    }

    protected Ingredient(Parcel in) {
        this.title = in.readString();
        this.mId = (UUID) in.readSerializable();
        this.stockPhoto = in.readString();
        this.type = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
