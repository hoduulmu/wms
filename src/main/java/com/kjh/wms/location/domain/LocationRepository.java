package com.kjh.wms.location.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocationRepository {

    private final Map<Long, Location> locations = new HashMap<>();
    private Long sequence = 1L;

    public void save(Location location) {
        location.assignNo(sequence++);
        locations.put(location.getLocationNo(), location);
    }

    public List<Location> findAll() {
        return List.copyOf(locations.values());
    }
}