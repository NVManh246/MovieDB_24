package com.framgia.nvmanh.boxmovie.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Company implements Parcelable {
    @SerializedName("id")
    private int mID;
    @SerializedName("logo_path")
    private String mLogoPath;
    @SerializedName("name")
    private String mName;

    public Company() {
    }

    protected Company(Parcel in) {
        mID = in.readInt();
        mLogoPath = in.readString();
        mName = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public int getID() {
        return mID;
    }

    public String getLogoPath() {
        return mLogoPath;
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
        parcel.writeInt(mID);
        parcel.writeString(mLogoPath);
        parcel.writeString(mName);
    }
}
