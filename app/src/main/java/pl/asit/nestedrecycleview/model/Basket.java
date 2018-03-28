package pl.asit.nestedrecycleview.model;

import java.util.ArrayList;

/**
 * Created by jczap on 28-03-2018.
 */

public class Basket {

    private String name;
    private ArrayList<Article> items;

    public Basket()
    {

    }

    public Basket(String name, ArrayList<Article> items)
    {
        this.name = name;
        this.items = items;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(ArrayList<Article> items)
    {
        this.items = items;
    }

    public ArrayList<Article> getItems()
    {
        return this.items;

    }
}
