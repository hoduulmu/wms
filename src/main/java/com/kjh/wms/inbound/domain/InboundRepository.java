package com.kjh.wms.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InboundRepository extends JpaRepository<Inbound, Long> {
    default Inbound getBy(Long inboundNo) {
        return findById(inboundNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고입니다. %d".formatted(inboundNo)));
    }

    default Inbound getByInboundItemNo(Long inboundItemNo) {
        return findByInboundItemNo(inboundItemNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고 상품입니다. %d".formatted(inboundItemNo)));
    }

    @Query("select i from Inbound i join fetch i.inboundItems ii where ii.inboundItemNo = :inboundItemNo")
    Optional<Inbound> findByInboundItemNo(Long inboundItemNo);
}
