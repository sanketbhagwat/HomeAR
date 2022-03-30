package com.example.homear;

public class cartItem {
    String name;
    int image;
    String color;
    String texture;
    String price;

    public cartItem(String name, int image, String color, String texture, String price) {
        this.name = name;
        this.image = image;
        this.color = color;
        this.texture = texture;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
