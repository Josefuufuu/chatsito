package edu.co.icesi.examjpatemplate.repository;

import edu.co.icesi.examjpatemplate.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    List<Bus> findByRoute_RouteNameIgnoreCase(String routeName);
}
