package com.kjh.wms.location.feature;

import com.kjh.wms.inbound.domain.LPN;
import com.kjh.wms.inbound.domain.LPNRepository;
import com.kjh.wms.location.domain.Location;
import com.kjh.wms.location.domain.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class AssignLocationLPNTest {

    private AssignLocationLPN assignLocationLPN;

    @BeforeEach
    void setUp() {
        assignLocationLPN = new AssignLocationLPN();
    }

    @Test
    @DisplayName("로케이션에 LPN 할당한다")
    void assignLocationLPN() {
        final String locationBarcode = "A-1-1";
        final String lpnBarcode = "LPN-1";
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(locationBarcode, lpnBarcode);
        assignLocationLPN.request(request);
    }

    private class AssignLocationLPN {
        private LocationRepository locationRepository;
        private LPNRepository lpnRepository;

        public void request(Request request) {
            Location location = locationRepository.getByLocationBarcode(request.locationBarcode());
            LPN lpn = lpnRepository.getByLpnBarcode(request.lpnBarcode());
            location.assignLPN(lpn);
        }

        public record Request(String locationBarcode,
                              String lpnBarcode) {
            public Request {
                Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
                Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
            }
        }
    }
}
