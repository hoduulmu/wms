package com.kjh.wms.inbound.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class InboundFixture {

    private Long inboundNo = 1L;
    private InboundStatus status = InboundStatus.REQUESTED;
    private String title = "상품명";
    private String description = "입고 설명";
    private LocalDateTime orderRequestedAt = LocalDateTime.now();
    private LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
    private List<InboundItemFixture> inboundItems = List.of(InboundItemFixture.anInboundItem());

    public static InboundFixture anInbound() {
        return new InboundFixture();
    }

    public static InboundFixture aConfirmedInbound() {
        return new InboundFixture().status(InboundStatus.CONFIRMED);
    }

    public InboundFixture inboundNo(Long inboundNo) {
        this.inboundNo = inboundNo;
        return this;
    }

    public InboundFixture status(InboundStatus status) {
        this.status = status;
        return this;
    }

    public InboundFixture title(String title) {
        this.title = title;
        return this;
    }

    public InboundFixture description(String description) {
        this.description = description;
        return this;
    }

    public InboundFixture orderRequestedAt(LocalDateTime orderRequestedAt) {
        this.orderRequestedAt = orderRequestedAt;
        return this;
    }

    public InboundFixture estimatedArrivalAt(LocalDateTime estimatedArrivalAt) {
        this.estimatedArrivalAt = estimatedArrivalAt;
        return this;
    }

    public InboundFixture inboundItems(InboundItemFixture... inboundItems) {
        this.inboundItems = Arrays.asList(inboundItems);
        return this;
    }

    public Inbound build() {
        return new Inbound(
                inboundNo,
                status,
                title,
                description,
                orderRequestedAt,
                estimatedArrivalAt,
                buildInboundItems()
        );
    }

    private List<InboundItem> buildInboundItems() {
        return inboundItems.stream().map(InboundItemFixture::build).toList();
    }
}
