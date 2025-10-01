package edu.co.icesi.examjpatemplate.controller;

import edu.co.icesi.examjpatemplate.entity.Route;
import edu.co.icesi.examjpatemplate.repository.RouteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping
    public List<Route> findAll() {
        return routeRepository.findAll();
    }
}
