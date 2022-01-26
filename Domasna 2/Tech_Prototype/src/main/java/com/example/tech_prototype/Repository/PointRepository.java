package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RestResource(exported = false)
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByTourismContaining(String preference);
}
