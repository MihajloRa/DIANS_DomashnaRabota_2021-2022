package com.example.tech_prototype.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class RouteDTO {
    DestinationCoordinates start;
    DestinationCoordinates current;
    List<DestinationCoordinates> destinations;
}
