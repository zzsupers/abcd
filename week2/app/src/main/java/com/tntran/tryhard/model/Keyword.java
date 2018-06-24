package com.tntran.tryhard.model;

/**
 * Created by TTN on 6/20/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("major")
    @Expose
    private Object major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Object getMajor() {
        return major;
    }

    public void setMajor(Object major) {
        this.major = major;
    }

}