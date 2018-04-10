package com.foodorderingapp.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "F:\\OFFICE\\@F1Soft\\10April\\images";
    private String FElocation = "F:\\OFFICE\\@F1Soft\\foa\\app\\assets\\images";

    public String getFElocation() {
        return FElocation;
    }

    public void setFElocation(String FElocation) {
        this.FElocation = FElocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

