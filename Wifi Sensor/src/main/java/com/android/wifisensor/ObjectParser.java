package com.android.wifisensor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rui on 14-2-5.
 */
public class ObjectParser<T> implements Serializable {
    int num_results;
    List<T> objects;
    int page;
    int total_pages;

}
