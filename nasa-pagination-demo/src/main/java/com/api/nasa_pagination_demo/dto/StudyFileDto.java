package com.api.nasa_pagination_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudyFileDto {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("date_created")
    private double dateCreated;

    @JsonProperty("date_updated")
    private double dateUpdated;

    @JsonProperty("file_size")
    private long fileSize;

    @JsonProperty("organization")
    private String organization;

    @JsonProperty("remote_url")
    private String remoteUrl;

    @JsonProperty("restricted")
    private boolean restricted;

    @JsonProperty("category")
    private String category;

    @JsonProperty("subcategory")
    private String subcategory;

    @JsonProperty("subdirectory")
    private String subdirectory;

    @JsonProperty("visible")
    private boolean visible;

    // Getters y setters
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(double dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getDateUpdated() {
        return dateUpdated;
    }
    public void setDateUpdated(double dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }
    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public boolean isRestricted() {
        return restricted;
    }
    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubdirectory() {
        return subdirectory;
    }
    public void setSubdirectory(String subdirectory) {
        this.subdirectory = subdirectory;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}