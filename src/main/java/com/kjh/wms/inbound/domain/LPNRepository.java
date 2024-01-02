package com.kjh.wms.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LPNRepository extends JpaRepository<LPN, Long> {
    boolean existsByLpnBarcode(String lpnBarcode);

    default LPN getByLpnBarcode(String lpnBarcode) {
        return findByLpnBarcode(lpnBarcode)
                .orElseThrow(() -> new IllegalArgumentException("해당 LPN을 찾을 수 없습니다. %s".formatted(lpnBarcode)));
    }

    Optional<LPN> findByLpnBarcode(String lpnBarcode);
}
