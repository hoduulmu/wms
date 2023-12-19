package com.kjh.wms.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LPNRepository extends JpaRepository<LPN, Long> {
    boolean existsByLpnBarcode(String lpnBarcode);
}
