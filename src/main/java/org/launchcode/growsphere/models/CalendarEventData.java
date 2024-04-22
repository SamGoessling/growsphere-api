package org.launchcode.growsphere.models;

import java.util.Date;

public class CalendarEventData {

    private String title;
    private Date startDate;
    private Date endDate;
    private String description;

    private String sowDescription;

    // Constructors
    public CalendarEventData() {
    }

    public CalendarEventData(String title, Date startDate, Date endDate, String description, String sowDescription) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.sowDescription = sowDescription;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSowDescription() {
        return sowDescription;
    }

    public void setSowDescription(String sowDescription) {
        this.sowDescription = sowDescription;
    }

    // toString() method
    @Override
    public String toString() {
        return "CalendarEventData{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}
