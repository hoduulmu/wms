package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundItem;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.inbound.domain.InboundStatus;
import com.kjh.wms.product.fixturer.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

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
        Long inboundNo = 1L;
        final Inbound inbound = new Inbound(
                "상품명",
                "입고 설명",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                List.of(new InboundItem(
                        ProductFixture.aProduct().build(),
                        1L,
                        1500L,
                        "상품 설명"
                ))
        );

        Mockito.when(inboundRepository.getBy(inboundNo))
                .thenReturn(inbound);
        confirmInbound.request(inboundNo);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    public static class ConfirmInbound {

        private final InboundRepository inboundRepository;

        public ConfirmInbound(InboundRepository inboundRepository) {
            this.inboundRepository = inboundRepository;
        }

        public void request(Long inboundNo) {
            final Inbound inbound = inboundRepository.getBy(inboundNo);
            inbound.confirmed();
        }
    }
}
