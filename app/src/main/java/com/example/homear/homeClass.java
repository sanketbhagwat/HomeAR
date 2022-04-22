package com.example.homear;
import java.util.List;
public class homeClass {
    String title;
    List<Categories> catgoriesList;
    public homeClass(){

    }

    public homeClass(String title, List<Categories> catgoriesList) {
        this.title = title;
        this.catgoriesList = catgoriesList;
    }

    public String getTitle() {
        return title;
    }
    public List<Categories> getChildItemList() {
        return catgoriesList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Categories> getCatgoriesList() {
        return catgoriesList;
    }


}
