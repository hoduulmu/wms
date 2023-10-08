package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.product.domain.*;
import com.kjh.wms.product.fixturer.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class RegisterInboundTest {

    private RegisterInbound registerInbound;
    private ProductRepository productRepository;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고를 등록한다")
    void registerInbound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(ProductFixture.aProduct().build()));

        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productNo,
                quantity,
                unitPrice,
                "description"
        );

        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimateArrivalAt = LocalDateTime.now().plusDays(1);
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);
        final RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "description",
                orderRequestedAt,
                estimateArrivalAt,
                inboundItems
        );
        registerInbound.request(request);
    }

}
