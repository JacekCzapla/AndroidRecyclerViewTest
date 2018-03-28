package pl.asit.nestedrecycleview.model;

/**
 * Created by jczap on 28-03-2018.
 */

public class Article {
    private String name;

    public Article(){

    }

    public Article(String name){
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
