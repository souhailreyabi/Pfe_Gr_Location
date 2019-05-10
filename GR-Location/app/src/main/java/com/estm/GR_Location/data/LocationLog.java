package com.estm.GR_Location.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;


/**
 * Created by AbdellatifMANNOUCHE on 4/11/16.
 */

public class LocationLog implements Serializable {

    /**
     *  Model class for teacher_details database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName
    @DatabaseField(generatedId = true, columnName = "locid")
    public int loc_id;

    // Define a String type field to hold teacher's name
    @DatabaseField(columnName = "lon")
    public String lon;

    @DatabaseField(columnName = "lat")
    public String lat;

    @DatabaseField(columnName = "datelog")
    public long datelog;


    // Default constructor is needed for the SQLite, so make sure you also have it
    public LocationLog(){

    }

    //For our own purpose, so it's easier to create a TeacherDetails object
    public LocationLog(final String lon, final String lat){
        this.lon = lon;
        this.lat = lat;
        datelog = System.currentTimeMillis()/1000;
        //this.teacherName = name;
        //this.address = address;
    }
}