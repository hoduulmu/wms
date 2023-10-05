package com.kjh.wms.product.feature;

import com.kjh.wms.product.domain.*;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

public class RegisterProduct {

    private final ProductRepository productRepository;

    public RegisterProduct(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void request(final Request request) {
        Product product = request.toDomain();
        productRepository.save(product);
    }

    public record Request(String name,
                          String code,
                          String description,
                          String brand,
                          String maker,
                          String origin,
                          Category electronics,
                          TemperatureZone roomTemperature,
                          Long weightInGrams,
                          Long widthInMillimeters,
                          Long heightInMillimeters,
                          Long lengthInMillimeters) {

        public Request {
            hasText(name, "상품명은 필수입니다");
            hasText(code, "상품 코드는 필수입니다");
            hasText(description, "상품 설명은 필수입니다");
            hasText(brand, "브랜드는 필수입니다");
            hasText(maker, "제조사는 필수입니다");
            hasText(origin, "원산지는 필수입니다");
            notNull(electronics, "카테고리는 필수입니다");
            notNull(roomTemperature, "온도대는 필수입니다");
            notNull(weightInGrams, "상품 무게는 필수입니다");
            notNull(widthInMillimeters, "상품 너비는 필수입니다");
            notNull(heightInMillimeters, "상품 높이는 필수입니다");
            notNull(lengthInMillimeters, "상품 길이는 필수입니다");
            if (0 > weightInGrams) {
                throw new IllegalArgumentException("상품 무게는 0보다 커야 합니다");
            }
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

        public Product toDomain() {
            return new Product(
                    name,
                    code,
                    description,
                    brand,
                    maker,
                    origin,
                    electronics,
                    roomTemperature,
                    weightInGrams,
                    new ProductSize(
                            widthInMillimeters,
                            heightInMillimeters,
                            lengthInMillimeters
                    )
            );
        }
    }
}
