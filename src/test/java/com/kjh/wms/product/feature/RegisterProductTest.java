package com.kjh.wms.product.feature;

import com.kjh.wms.common.ApiTest;
import com.kjh.wms.common.Scenario;
import com.kjh.wms.product.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterProductTest extends ApiTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품을 등록한다")
    void registerProduct() {
        Scenario.registerProduct().request();
        assertThat(productRepository.findAll()).hasSize(1);
    }
}
