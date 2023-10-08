package com.kjh.wms.inbound.domain;

import com.kjh.wms.product.domain.Product;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Comment("입고 상품")
@Table(name = "inbound_item")
@Entity
public class InboundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inbound_item_no")
    @Comment("입고 상품 번호")
    private Long inboundItemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", nullable = false)
    @Comment("상품")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @Comment("수량")
    private Long quantity;

    @Column(name = "unit_price", nullable = false)
    @Comment("단가")
    private Long unitPrice;

    @Column(name = "description", nullable = false)
    @Comment("입고 상품 설명")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_no", nullable = false)
    @Comment("입고")
    private Inbound inbound;

    public InboundItem(final Product product,
                       final Long quantity,
                       final Long unitPrice,
                       final String description) {
        validateConstructor(product, quantity, unitPrice, description);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    private void validateConstructor(final Product product,
                                     final Long quantity,
                                     final Long unitPrice,
                                     final String description) {
        Assert.notNull(product, "상품은 필수입니다");
        Assert.notNull(quantity, "수량은 필수입니다");
        Assert.notNull(unitPrice, "단가는 필수입니다");
        Assert.hasText(description, "품목 설명은 필수입니다");

        Assert.isTrue(quantity >= 1, "수량은 1개 이상이어야 합니다");
        Assert.isTrue(unitPrice >= 0, "단가는 0원 이상이어야 합니다");
    }

    public void assignInbound(Inbound inbound) {
        Assert.notNull(inbound, "입고는 필수입니다");
        this.inbound = inbound;
    }
}
