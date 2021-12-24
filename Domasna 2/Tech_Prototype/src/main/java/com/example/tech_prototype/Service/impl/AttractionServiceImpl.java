package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Repository.PointRepository;
import com.example.tech_prototype.Service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {
    PointRepository repository;

    @Autowired
    AttractionServiceImpl(PointRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Point> getPoints() { return this.repository.findAll(); }


}
