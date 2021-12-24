package com.example.tech_prototype.Model;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

enum status {
    PAUSED,
    FINISHED,
    ONGOING
}

@Getter
@Setter
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "routes")
public class Route {
    status s;
    Geometry start;
    Geometry current;

    @OneToMany
    List<Point> destinations;

    @Id
    @ManyToOne
    User u;
}
