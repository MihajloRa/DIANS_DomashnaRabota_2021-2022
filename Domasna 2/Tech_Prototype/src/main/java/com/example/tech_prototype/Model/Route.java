package com.example.tech_prototype.Model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Routes")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="route_id")
    long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status s;
    @Column(name="start")
    @Type(type = "org.hibernate.spatial.JTSGeometryType")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    Geometry start;
    @Column(name="current")
    @Type(type = "org.hibernate.spatial.JTSGeometryType")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    Geometry current;

    @ManyToMany
    List<Point> destinations;

    @ManyToOne
    User user;

    public Route(User user, Geometry start, ArrayList<Point> destinations){
        this.user = user;
        this.start = start;
        this.current = start;
        this.s = Status.ONGOING;
        this.destinations = destinations;
    }
}
