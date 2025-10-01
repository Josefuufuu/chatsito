package edu.co.icesi.examjpatemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String licensePlate;

    @ManyToOne
    private Route route;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<GeoPoint> geoPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<GeoPoint> getGeoPoints() {
        return geoPoints;
    }

    public void setGeoPoints(List<GeoPoint> geoPoints) {
        this.geoPoints = geoPoints;
    }
}