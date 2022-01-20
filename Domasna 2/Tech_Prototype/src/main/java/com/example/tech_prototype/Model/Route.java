package com.example.tech_prototype.Model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    Status s;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    Geometry start;

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
