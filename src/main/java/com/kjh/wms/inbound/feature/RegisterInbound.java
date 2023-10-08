package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundItem;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.product.domain.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegisterInbound {

    private final ProductRepository productRepository;
    private final InboundRepository inboundRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/inbounds")
    public void request(@RequestBody @Valid Request request) {
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

    public record Request(
            @NotBlank(message = "입고 제목은 필수입니다")
            String title,
            @NotBlank(message = "입고 설명은 필수입니다")
            String description,

            @NotNull(message = "입고 요청일은 필수입니다")
            LocalDateTime orderRequestedAt,

            @NotNull(message = "입고 예정일은 필수입니다")
            LocalDateTime estimateArrivalAt,

            @NotNull(message = "입고 품목은 필수입니다")
            List<Request.Item> inboundItems) {

        public record Item(
                @NotNull(message = "상품 번호는 필수입니다")
                Long productNo,

                @NotNull(message = "수량은 필수입니다")
                @Min(value = 1, message = "수량은 1개 이상이어야 합니다")
                Long quantity,

                @NotNull(message = "단가는 필수입니다")
                @Min(value = 0, message = "단가는 0원 이상이어야 합니다")
                Long unitPrice,

                @NotBlank(message = "품목 설명은 필수입니다")
                String description) {
        }
    }

}
