package com.example.homear;

public class Categories {
    String catName;
    int image;
    public Categories(){}
    public Categories(String catName,int image){
        this.catName =catName;
        this.image =image;

    }

    public String getName() {
        return catName;
    }

    public void setName(String name) {
        this.catName = name;
    }

    public int getImg() {
        return image;
    }

    public void setImg(int img) {
        this.image = img;
    }
}
