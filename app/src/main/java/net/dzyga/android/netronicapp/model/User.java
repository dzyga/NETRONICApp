package net.dzyga.android.netronicapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mr.Dzyga on 14.07.2020.
 */
public class User {

    public class Name {
        @SerializedName("title")
        public String title = "";
        @SerializedName("first")
        public String first = "";
        @SerializedName("last")
        public String last = "";
    }

    public class Picture {
        @SerializedName("large")
        public String large = "";
        @SerializedName("medium")
        public String medium = "";
        @SerializedName("thumbnail")
        public String thumbnail = "";
    }

    @SerializedName("gender")
    public String gender = "";
    @SerializedName("name")
    public Name name;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;
    @SerializedName("cell")
    public String cell;
    @SerializedName("picture")
    public Picture picture;
    @SerializedName("nat")
    public String nat;

    public String getName(){
        return name.title+". "+name.first+" "+name.last;
    }

    public String getThumbnail() {
        return picture.thumbnail;
    }

    public String getPicture(){
        return picture.large;
    }
}
