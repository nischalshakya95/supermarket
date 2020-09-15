package com.dinube.supermarket.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data<T> {

    @SerializedName("data")
    private List<T> t;

    public List<T> getList() {
        return t;
    }

    public void setList(List<T> t) {
        this.t = t;
    }
}
