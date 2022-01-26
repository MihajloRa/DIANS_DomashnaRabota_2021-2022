package com.example.tech_prototype.Model.DTOs;

import lombok.Data;

@Data
public class DestinationCoordinates {
    double latitude;
    double longitude;

    public DestinationCoordinates(double x, double y) {
        this.latitude = x;
        this.longitude = y;
    }
}
