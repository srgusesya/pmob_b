package net.husnilkamil.layartancap.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("results")
    public List<MovieItem> results;

}
