package edu.co.icesi.examjpatemplate.controller;

import edu.co.icesi.examjpatemplate.entity.Bus;
import edu.co.icesi.examjpatemplate.entity.GeoPoint;
import edu.co.icesi.examjpatemplate.repository.BusRepository;
import edu.co.icesi.examjpatemplate.repository.GeoPointRepository;
import edu.co.icesi.examjpatemplate.repository.RouteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransitController {

    private final BusRepository busRepository;
    private final GeoPointRepository geoPointRepository;
    private final RouteRepository routeRepository;

    public TransitController(BusRepository busRepository,
                             GeoPointRepository geoPointRepository,
                             RouteRepository routeRepository) {
        this.busRepository = busRepository;
        this.geoPointRepository = geoPointRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/routes/{routeName}/buses")
    public ResponseEntity<List<Bus>> getBusesByRoute(@PathVariable String routeName) {
        List<Bus> buses = busRepository.findByRoute_RouteNameIgnoreCase(routeName);
        if (buses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/buses/{plate}/geopoints")
    public ResponseEntity<List<GeoPoint>> getGeoPointsByBus(@PathVariable("plate") String licensePlate) {
        List<GeoPoint> geoPoints = geoPointRepository.findByBus_LicensePlateOrderByTimestampAsc(licensePlate);
        if (geoPoints.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(geoPoints);
    }

    @GetMapping("/buses/{plate}/last-location")
    public ResponseEntity<GeoPoint> getLastLocationByBus(@PathVariable("plate") String licensePlate) {
        Optional<GeoPoint> lastGeoPoint = geoPointRepository.findFirstByBus_LicensePlateOrderByTimestampDesc(licensePlate);
        return lastGeoPoint.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
