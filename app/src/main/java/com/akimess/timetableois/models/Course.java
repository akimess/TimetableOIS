package com.akimess.timetableois.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by akimess on 11/09/2017.
 */

public class Course {
    private String name;
    private String lecturer;
    private String type;
    private String location;
    private String code;
    private Date startTime;
    private Date endTime;


    public Course(){}

    public Course(String name, String lecturer, String type, String location, String code, String startTime, String endTime) {
        this.name = name;
        this.lecturer = lecturer;
        this.type = type;
        this.location = location;
        this.code = code;
        //"2017-09-12 16:00:00"
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try{
            this.startTime = format.parse(startTime);
            this.endTime = format.parse(endTime);
        }catch (java.text.ParseException e){
            e.printStackTrace();
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date time) {
        this.startTime = time;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date time) {
        this.endTime = time;
    }
}
