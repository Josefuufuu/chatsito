package edu.co.icesi.examjpatemplate.repository;

import edu.co.icesi.examjpatemplate.entity.Bus;
import edu.co.icesi.examjpatemplate.entity.GeoPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RepositoryIntegrationTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private GeoPointRepository geoPointRepository;

    @Test
    void shouldLoadRoutesFromDataSql() {
        assertThat(routeRepository.findAll()).hasSize(4);
    }

    @Test
    void shouldFindBusesByRouteNameIgnoreCase() {
        List<Bus> buses = busRepository.findByRoute_RouteNameIgnoreCase("e21");
        assertThat(buses)
                .hasSize(3)
                .extracting(Bus::getLicensePlate)
                .containsExactlyInAnyOrder("AAA111", "BBB222", "III999");
    }

    @Test
    void shouldReturnOrderedGeoPointsForBus() {
        List<GeoPoint> geoPoints = geoPointRepository.findByBus_LicensePlateOrderByTimestampAsc("AAA111");
        assertThat(geoPoints)
                .hasSize(5)
                .isSortedAccordingTo(Comparator.comparing(GeoPoint::getTimestamp));
        assertThat(geoPoints.get(0).getLatitude()).isEqualTo(3.4516);
        assertThat(geoPoints.get(geoPoints.size() - 1).getLongitude()).isEqualTo(-76.5050);
    }

    @Test
    void shouldReturnLatestGeoPoint() {
        Optional<GeoPoint> latest = geoPointRepository.findFirstByBus_LicensePlateOrderByTimestampDesc("AAA111");
        assertThat(latest).isPresent();
        assertThat(latest.get().getTimestamp()).isEqualTo(LocalDateTime.of(2025, 9, 12, 11, 11, 40));
    }
}
