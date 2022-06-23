package com.example.tech_prototype.Model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Subselect("SELECT osm_id, way, tourism FROM planet_osm_point WHERE tourism IS NOT NULL")
@Immutable
public class PlanetOsmPointTourismView {

    @Id
    @Column(name = "osm_id")
    @JsonInclude
    private Long osmId;

    @Column(name = "way")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @JsonInclude
    private Geometry way;

    @Column(name = "tourism")
    @JsonInclude
    private String tourism;
}
