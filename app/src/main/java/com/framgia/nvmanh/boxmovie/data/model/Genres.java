package com.framgia.nvmanh.boxmovie.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Genres implements Parcelable{
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    public Genres(int id, String name) {
        mId = id;
        this.mName = name;
    }

    protected Genres(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
    }

    public static final class Key {
        public static final String POPULAR = "popular";
        public static final String TOP_RATED = "top_rated";
        public static final String NOW_PLAYING = "now_playing";
        public static final String UPCOMING = "upcoming";
    }
}
