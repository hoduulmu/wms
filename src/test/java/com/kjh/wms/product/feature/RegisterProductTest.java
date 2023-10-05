package com.kjh.wms.product.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

class RegisterProductTest {

    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        registerProduct = new RegisterProduct(productRepository);
    }

    @Test
    @DisplayName("상품을 등록한다")
    void registerProduct() {
        final String name = "name";
        final String code = "code";
        final String description = "description";
        final String brand = "brand";
        final String maker = "maker";
        final String origin = "origin";
        final Long weightInGrams = 1000L;
        final Long widthInMillimeters = 100L;
        final Long heightInMillimeters = 100L;
        final Long lengthInMillimeters = 100L;

        RegisterProduct.Request request = new RegisterProduct.Request(
                name,
                code,
                description,
                brand,
                maker,
                origin,
                Category.ELECTRONICS,
                TemperatureZone.ROOM_TEMPERATURE,
                weightInGrams, // gram
                widthInMillimeters, // 너비 mm
                heightInMillimeters, // 높이 mm
                lengthInMillimeters // 길이 mm
        );

        registerProduct.request(request);

        assertThat(productRepository.findAll()).hasSize(1);
    }

    public static class RegisterProduct {

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

    public enum Category {
        ELECTRONICS("전자 제품");

        private final String description;

        Category(final String description) {
            this.description = description;
        }
    }

    public enum TemperatureZone {
        ROOM_TEMPERATURE("상온");
        private final String description;

        TemperatureZone(final String description) {
            this.description = description;
        }
    }

    public static class ProductSize {
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

    public static class Product {

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

        public Long getId() {
            return id;
        }
    }

    public static class ProductRepository {

        private final Map<Long, Product> products = new HashMap<>();
        private Long nextId = 1L;

        public void save(Product product) {
            product.assignId(nextId++);
            products.put(product.getId(), product);
        }

        public List<Product> findAll() {
            return List.copyOf(products.values());
        }
    }
}
