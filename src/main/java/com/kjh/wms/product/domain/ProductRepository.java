package com.kjh.wms.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCode(String code);

    default Product getBy(Long productNo) {
        return findById(productNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. %d".formatted(productNo)));
    }
}
