package com.kjh.wms.location.domain;

import com.kjh.wms.inbound.domain.LPN;
import com.kjh.wms.inbound.domain.LPNFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest {

    @Test
    @DisplayName("로케이션에 LPN을 할당한다")
    void assignLPN() {
        Location location = LocationFixture.aLocation().build();
        LPN lpn = LPNFixture.anLPN().build();
        location.assignLPN(lpn);
        assertThat(location.getLocationLPNs()).hasSize(1);
        assertThat(location.getLocationLPNs().get(0).getInventoryQuantity()).isEqualTo(1);
    }

}
