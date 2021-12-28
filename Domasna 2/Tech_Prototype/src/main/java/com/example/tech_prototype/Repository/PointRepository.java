package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByTourismIsContaining(String preference);
}
