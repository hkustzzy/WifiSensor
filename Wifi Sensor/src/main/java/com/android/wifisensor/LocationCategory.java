package com.android.wifisensor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rui on 14-2-5.
 */
public class LocationCategory implements Serializable {
    @Expose @SerializedName("id") private int id;
    @Expose @SerializedName("name") private String name;
    @Expose @SerializedName("locations") private ArrayList<Location> locations;

    public LocationCategory(int id, String name, ArrayList<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Location> getLocations() {

        if(this.locations == null)
            this.locations = new ArrayList<Location>();
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public void assignLocation(Location location) {
        if(this.locations == null) this.locations = new ArrayList<Location>();
        if(this.locations.contains(location)) return;
        this.locations.add(location);
    }
}
