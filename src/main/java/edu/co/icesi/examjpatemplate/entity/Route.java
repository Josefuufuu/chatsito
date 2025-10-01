package edu.co.icesi.examjpatemplate.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routeName;

    private String type;

    @OneToMany(mappedBy = "buses", cascade = CascadeType.ALL)
    @JoinColumn(name="route_id")
    private List<Bus> buses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
}
