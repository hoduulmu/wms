package com.kjh.wms.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundRepository extends JpaRepository<Inbound, Long> {
    default Inbound getBy(Long inboundNo) {
        return findById(inboundNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고입니다. %d".formatted(inboundNo)));
    }
}
