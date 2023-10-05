package com.kjh.wms.product.domain;

import static org.springframework.util.Assert.notNull;

public class ProductSize {
    private final Long widthInMillimeters;
    private final Long heightInMillimeters;
    private final Long lengthInMillimeters;

    public ProductSize(final Long widthInMillimeters,
                       final Long heightInMillimeters,
                       final Long lengthInMillimeters) {
        validateProductSize(widthInMillimeters, heightInMillimeters, lengthInMillimeters);
        this.widthInMillimeters = widthInMillimeters;
        this.heightInMillimeters = heightInMillimeters;
        this.lengthInMillimeters = lengthInMillimeters;
    }

    private void validateProductSize(Long widthInMillimeters,
                                     Long heightInMillimeters,
                                     Long lengthInMillimeters) {
        notNull(widthInMillimeters, "상품 너비는 필수입니다");
        notNull(heightInMillimeters, "상품 높이는 필수입니다");
        notNull(lengthInMillimeters, "상품 길이는 필수입니다");
        if (0 > widthInMillimeters) {
            throw new IllegalArgumentException("상품 너비는 0보다 커야 합니다");
        }
        if (0 > heightInMillimeters) {
            throw new IllegalArgumentException("상품 높이는 0보다 커야 합니다");
        }
        if (0 > lengthInMillimeters) {
            throw new IllegalArgumentException("상품 길이는 0보다 커야 합니다");
        }
    }
}
