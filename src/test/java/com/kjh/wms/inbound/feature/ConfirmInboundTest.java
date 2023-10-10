package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        confirmInbound = new ConfirmInbound(inboundRepository);
    }

    @Test
    @DisplayName("입고를 승인한다")
    void confirmInbound() {
        final Long inboundNo = 1L;
        final Inbound inbound = InboundFixture.anInbound().build();
        Mockito.when(inboundRepository.getBy(inboundNo))
                .thenReturn(inbound);

        confirmInbound.request(inboundNo);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }
}
