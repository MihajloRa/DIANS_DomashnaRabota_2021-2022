package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
