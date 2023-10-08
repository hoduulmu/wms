package com.kjh.wms.inbound.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InboundRepository {
    private final Map<Long, Inbound> inbounds = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Inbound inbound) {
        inbound.assignId(sequence++);
        inbounds.put(inbound.getId(), inbound);
    }

    public List<Inbound> findAll() {
        return inbounds.values()
                .stream()
                .toList();
    }
}
