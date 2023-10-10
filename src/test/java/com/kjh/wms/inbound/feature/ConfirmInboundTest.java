package com.kjh.wms.inbound.feature;

import com.kjh.wms.common.ApiTest;
import com.kjh.wms.common.Scenario;
import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.inbound.domain.InboundStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmInboundTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고를 승인한다")
    void confirmInbound() {

        Scenario
                .registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request();

        final Inbound inbound = inboundRepository.getBy(1L);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }
}
