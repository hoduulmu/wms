package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        confirmInbound = new ConfirmInbound();
    }

    @Test
    @DisplayName("입고를 승인한다")
    void confirmInbound() {
        Long inboundNo = 1L;

        confirmInbound.request(inboundNo);

//        assertThat(inboundRepository.findById(inboundNo).get().getStatus()).isEqualTo("");
    }

    public static class ConfirmInbound {

        private InboundRepository inboundRepository;

        public void request(Long inboundNo) {
            final Inbound inbound = inboundRepository.findById(inboundNo).orElseThrow();
            inbound.confirmed();
        }
    }
}
