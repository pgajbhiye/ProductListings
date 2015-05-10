package com.products.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pallavi on 18-Apr-15.
 */


public class ProductInfo implements Parcelable {

    private String name;
    @SerializedName("imagee")
    private String image;
    private String type;
    private String price;
    private String rating;
    private String users;
    private String last_update;
    private String description;
    private String url;

    public ProductInfo(Parcel source) {
        name = source.readString();
        image = source.readString();
        //price = source.readString();

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        // hey, BitmapDrawable class implements Parcelable.
        // so we can write the object directly
       // dest.writeValue(((BitmapDrawable)profilePicture).getBitmap());
    }

    public static final Parcelable.Creator<ProductInfo> CREATOR = new Parcelable.Creator<ProductInfo>() {
        public ProductInfo createFromParcel(Parcel source) {
            return new ProductInfo(source);
        }

        public ProductInfo[] newArray(int size) {
            return new ProductInfo[size];
        }
    };
}
