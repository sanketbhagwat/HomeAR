package com.example.homear;

import android.os.Parcel;
import android.os.Parcelable;

public class products implements Parcelable {
    String img;
    String title;
    String price;
    String model;
    String key;

    public products(String img,String title,String price,String model,String key){
        this.img=img;
        this.title=title;
        this.price=price;
        this.model=model;
        this.key=key;
    }

    protected products(Parcel in) {
        img = in.readString();
        title = in.readString();
        price = in.readString();
        model = in.readString();
        key = in.readString();
    }

    public static final Creator<products> CREATOR = new Creator<products>() {
        @Override
        public products createFromParcel(Parcel in) {
            return new products(in);
        }

        @Override
        public products[] newArray(int size) {
            return new products[size];
        }
    };

    public String getKey(){
        return this.key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(img);
        parcel.writeString(title);
        parcel.writeString(price);
        parcel.writeString(model);
        parcel.writeString(key);
    }
}
