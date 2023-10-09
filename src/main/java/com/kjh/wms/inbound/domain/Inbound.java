package com.kjh.wms.inbound.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Comment("입고")
@Table(name = "inbound")
@Entity
public class Inbound {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inbound_no")
    @Comment("입고 번호")
    private Long inboundNo;

    @Column(name = "title", nullable = false)
    @Comment("입고 제목")
    private String title;

    @Column(name = "description", nullable = false)
    @Comment("입고 설명")
    private String description;

    @Column(name = "order_requested_at", nullable = false)
    @Comment("입고 요청일시")
    private LocalDateTime orderRequestedAt;

    @Column(name = "estimated_arrival_at", nullable = false)
    @Comment("입고 예정일시")
    private LocalDateTime estimatedArrivalAt;

    @OneToMany(mappedBy = "inbound", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InboundItem> inboundItems = new ArrayList<>();

    @Getter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Comment("입고 진행 상태")
    private InboundStatus status = InboundStatus.REQUESTED;

    public Inbound(String title,
                   String description,
                   LocalDateTime orderRequestedAt,
                   LocalDateTime estimatedArrivalAt,
                   List<InboundItem> inboundItems) {
        validateConstructor(
                title,
                description,
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems);
        this.title = title;
        this.description = description;
        this.orderRequestedAt = orderRequestedAt;
        this.estimatedArrivalAt = estimatedArrivalAt;
        for (InboundItem inboundItem : inboundItems) {
            this.inboundItems.add(inboundItem);
            inboundItem.assignInbound(this);
        }
    }

    private void validateConstructor(final String title,
                                     final String description,
                                     final LocalDateTime orderRequestedAt,
                                     final LocalDateTime estimateArrivalAt,
                                     final List<InboundItem> inboundItems) {
        Assert.hasText(title, "입고 제목은 필수입니다");
        Assert.hasText(description, "입고 설명은 필수입니다");
        Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다");
        Assert.notNull(estimateArrivalAt, "입고 예정일은 필수입니다");
        Assert.notEmpty(inboundItems, "입고 품목은 필수입니다");
    }

    public void confirmed() {
        validateConfirmStatus();
        status = InboundStatus.CONFIRMED;
    }

    private void validateConfirmStatus() {
        if (status != InboundStatus.REQUESTED) {
            throw new IllegalStateException("입고 요청 상태가 아닙니다");
        }
    }
}
