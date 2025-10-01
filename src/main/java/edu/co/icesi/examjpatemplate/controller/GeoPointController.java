package edu.co.icesi.examjpatemplate.controller;

import edu.co.icesi.examjpatemplate.entity.GeoPoint;
import edu.co.icesi.examjpatemplate.repository.GeoPointRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/geopoints")
public class GeoPointController {

    private final GeoPointRepository geoPointRepository;

    public GeoPointController(GeoPointRepository geoPointRepository) {
        this.geoPointRepository = geoPointRepository;
    }

    @GetMapping("/buses/{licensePlate}")
    public List<GeoPoint> findRoute(@PathVariable String licensePlate) {
        return geoPointRepository.findByBus_LicensePlateOrderByTimestampAsc(licensePlate);
    }

    @GetMapping("/buses/{licensePlate}/latest")
    public ResponseEntity<GeoPoint> findLatest(@PathVariable String licensePlate) {
        return geoPointRepository.findFirstByBus_LicensePlateOrderByTimestampDesc(licensePlate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
