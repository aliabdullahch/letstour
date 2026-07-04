package com.tourMaster.letsTour.DTOs;


public class DestinationDescriptionDTO {
    private String id;
    private String description;

    public DestinationDescriptionDTO(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public DestinationDescriptionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
