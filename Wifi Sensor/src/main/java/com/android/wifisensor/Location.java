package com.android.wifisensor;


import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {

    private double latitude;
    private double longitude;
    private int id;
    private int category_id;
    private String name;
    //private ArrayList<Task> tasks;
    private LocationCategory category;

    public Location(int id, int category_id, String name) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        //this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public ArrayList<Task> getTasks() {
        if( this.tasks == null ) this.tasks = new ArrayList<Task>();
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
*/
    public LocationCategory getCategory() {
        return category;
    }

    public void setCategory(LocationCategory category) {
        this.category = category;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }
}
