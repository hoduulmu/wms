package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundItem;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.product.domain.ProductRepository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterInbound {

    private final ProductRepository productRepository;
    private final InboundRepository inboundRepository;

    public RegisterInbound(final ProductRepository productRepository, final InboundRepository inboundRepository) {
        this.productRepository = productRepository;
        this.inboundRepository = inboundRepository;
    }

    public void request(Request request) {
        final Inbound inbound = createInbound(request);
        inboundRepository.save(inbound);
    }

    private Inbound createInbound(Request request) {
        return new Inbound(
                request.title(),
                request.description(),
                request.orderRequestedAt(),
                request.estimateArrivalAt(),
                mapToInboundItems(request));
    }

    private List<InboundItem> mapToInboundItems(Request request) {
        return request.inboundItems.stream()
                .map(this::newInboundItem)
                .toList();
    }

    private InboundItem newInboundItem(Request.Item item) {
        return new InboundItem(
                productRepository.getBy(item.productNo()),
                item.quantity(),
                item.unitPrice(),
                item.description());
    }

    public record Request(String title,
                          String description,
                          LocalDateTime orderRequestedAt,
                          LocalDateTime estimateArrivalAt,
                          List<Request.Item> inboundItems) {

        public Request {
            Assert.hasText(title, "입고 제목은 필수입니다");
            Assert.hasText(description, "입고 설명은 필수입니다");
            Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다");
            Assert.notNull(estimateArrivalAt, "입고 예정일은 필수입니다");
            Assert.notEmpty(inboundItems, "입고 품목은 필수입니다");
        }

        public record Item(Long productNo,
                           Long quantity,
                           Long unitPrice,
                           String description) {

            public Item {
                Assert.notNull(productNo, "상품 번호는 필수입니다");
                Assert.notNull(quantity, "수량은 필수입니다");
                Assert.notNull(unitPrice, "단가는 필수입니다");
                Assert.hasText(description, "품목 설명은 필수입니다");

                Assert.isTrue(quantity >= 1, "수량은 1개 이상이어야 합니다");
                Assert.isTrue(unitPrice >= 0, "단가는 0원 이상이어야 합니다");
            }
        }
    }

}
