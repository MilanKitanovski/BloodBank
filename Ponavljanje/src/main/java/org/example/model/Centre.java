package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.util.Date;

@javax.persistence.Entity
public class Centre extends Entity{

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private double longitude;
    @NotNull
    private double latitude;
    @NotNull
    private String description;
    private double avgGrade;

    private String city;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column
    private Date startWork;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column
    private Date endWork;

    public Centre() {
    }
    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    public Date getEndWork() {
        return endWork;
    }

    public void setEndWork(Date endWork) {
        this.endWork = endWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
