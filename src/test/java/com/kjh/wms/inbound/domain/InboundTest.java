package com.kjh.wms.inbound.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InboundTest {

    @Test
    @DisplayName("입고를 승인한다")
    void confirmed() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        inbound.confirmed();

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    @Test
    @DisplayName("입고를 승인한다 - 실패 입고의 상태가 요청이 아닌 경우 예외가 발생한다")
    void fail_invalid_status_confirmed() {
        final Inbound confiredInbound = InboundFixture.aConfirmedInbound().build();

        assertThatThrownBy(confiredInbound::confirmed)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("입고 요청 상태가 아닙니다");
    }

    @Test
    @DisplayName("입고를 반려/거부하면 입고의 상태가 REJECTED로 변경된다")
    void reject() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        final String rejectionReason = "반려 사유";
        inbound.reject(rejectionReason);

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

    @Test
    @DisplayName("입고를 반려/거부한다 - 실패 입고의 상태가 요청이 아닌 경우 예외가 발생한다")
    void fail_invalid_status_reject() {
        final Inbound confiredInbound = InboundFixture.aConfirmedInbound().build();

        assertThatThrownBy(() -> confiredInbound.reject("rejectionReason"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("입고 요청 상태가 아닙니다");
    }

    @Test
    @DisplayName("LPN을 등록한다")
    void registerLPN() {
        final Inbound inbound = InboundFixture.aConfirmedInbound().build();
        final long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

        inbound.registerLPN(inboundItemNo, lpnBarcode, expirationAt);


    }
}
