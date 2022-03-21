package com.example.homear;

public class products {
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

    public String getKey(){
        return this.key;
    }

}
