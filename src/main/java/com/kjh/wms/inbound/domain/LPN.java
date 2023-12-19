package com.kjh.wms.inbound.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Comment("LPN")
@Entity
@Table(name = "lpn")
public class LPN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("LPN 번호")
    private Long lpnNo;

    @Column(name = "lpn_barcode", nullable = false, unique = true)
    @Comment("LPN 바코드")
    private String lpnBarcode;

    @Column(name = "expiration_at", nullable = false)
    @Comment("유통기한")
    private LocalDateTime expirationAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_item_id", nullable = false)
    @Comment("입고 아이템 ID")
    private InboundItem inboundItem;

    public LPN(String lpnBarcode,
               LocalDateTime expirationAt,
               InboundItem inboundItem) {
        validateConstructor(lpnBarcode, expirationAt, inboundItem);
        this.lpnBarcode = lpnBarcode;
        this.expirationAt = expirationAt;
        this.inboundItem = inboundItem;
    }

    private void validateConstructor(String lpnBarcode,
                                     LocalDateTime expirationAt,
                                     InboundItem inboundItem) {
        Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
        Assert.notNull(expirationAt, "유통기한은 필수입니다.");
        Assert.notNull(inboundItem, "입고 상품은 필수입니다.");
    }
}
