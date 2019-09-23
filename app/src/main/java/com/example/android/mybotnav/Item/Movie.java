package com.example.android.mybotnav.Item;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String name;
    private String photo1;
    private String photo2;
    private String description;
    private String date;
    private String year;
    private float rating;
    private int id;
    private JSONArray genre;
    private String allgenre;
    private String allproduction;
    private String tagline;

    public Movie(JSONObject jObject) {
        try {
            id = jObject.getInt("id");
            name = jObject.getString("original_title");
            photo1 = jObject.getString("poster_path");
            rating = (float) jObject.getDouble("vote_average");
            description = jObject.getString("overview");
            date = jObject.getString("release_date");
            photo2 = jObject.getString("backdrop_path");
            genre = jObject.getJSONArray("genres_ids");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Movie() {

    }

    protected Movie(Parcel in) {
        this.name = in.readString();
        this.photo1 = in.readString();
        this.photo2 = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.year = in.readString();
        this.rating = in.readFloat();
        this.id = in.readInt();
        this.genre = in.readParcelable(JSONArray.class.getClassLoader());
        this.allgenre = in.readString();
        this.allproduction = in.readString();
        this.tagline = in.readString();
    }

    public String getAllgenre() {
        return allgenre;
    }

    public void setAllgenre(String allgenre) {
        this.allgenre = allgenre;
    }

    public String getAllproduction() {
        return allproduction;
    }

    public void setAllproduction(String allproduction) {
        this.allproduction = allproduction;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONArray getGenre() {
        return genre;
    }

    public void setGenre(JSONArray genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.photo1);
        dest.writeString(this.photo2);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.year);
        dest.writeFloat(this.rating);
        dest.writeInt(this.id);
        dest.writeParcelable((Parcelable) this.genre, flags);
        dest.writeString(this.allgenre);
        dest.writeString(this.allproduction);
        dest.writeString(this.tagline);
    }
}
