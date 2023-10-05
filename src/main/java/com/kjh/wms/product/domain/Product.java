package com.kjh.wms.product.domain;

import lombok.Getter;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

public class Product {

    @Getter
    private Long id;
    private final String name;
    private final String code;
    private final String description;
    private final String brand;
    private final String maker;
    private final String origin;
    private final Category electronics;
    private final TemperatureZone roomTemperature;
    private final Long weightInGrams;
    private final ProductSize productSize;


    public Product(String name,
                   String code,
                   String description,
                   String brand,
                   String maker,
                   String origin,
                   Category electronics,
                   TemperatureZone roomTemperature,
                   Long weightInGrams,
                   ProductSize productSize) {
        validateConstructor(name,
                code,
                description,
                brand,
                maker,
                origin,
                electronics,
                roomTemperature,
                weightInGrams,
                productSize);
        this.name = name;
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.maker = maker;
        this.origin = origin;
        this.electronics = electronics;
        this.roomTemperature = roomTemperature;
        this.weightInGrams = weightInGrams;
        this.productSize = productSize;
    }

    private void validateConstructor(String name,
                                     String code,
                                     String description,
                                     String brand,
                                     String maker,
                                     String origin,
                                     Category electronics,
                                     TemperatureZone roomTemperature,
                                     Long weightInGrams,
                                     ProductSize productSize) {
        hasText(name, "상품명은 필수입니다");
        hasText(code, "상품 코드는 필수입니다");
        hasText(description, "상품 설명은 필수입니다");
        hasText(brand, "브랜드는 필수입니다");
        hasText(maker, "제조사는 필수입니다");
        hasText(origin, "원산지는 필수입니다");
        notNull(electronics, "카테고리는 필수입니다");
        notNull(roomTemperature, "온도대는 필수입니다");
        notNull(weightInGrams, "상품 무게는 필수입니다");
        notNull(productSize, "상품 크기는 필수입니다");
        if (0 > weightInGrams) {
            throw new IllegalArgumentException("상품 무게는 0보다 커야 합니다");
        }
    }

    public void assignId(Long id) {
        this.id = id;
    }

}
