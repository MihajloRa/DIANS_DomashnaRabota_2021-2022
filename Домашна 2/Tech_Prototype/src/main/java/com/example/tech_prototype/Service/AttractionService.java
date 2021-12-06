package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttractionService {
    List<Point> getPoints();
}
