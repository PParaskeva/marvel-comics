package com.example.panagiotis.marvelcomics.Realm;

import io.realm.RealmObject;

public class ComicsRealm extends RealmObject {

    String id;
    String name;
    Double price;

    public ComicsRealm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
