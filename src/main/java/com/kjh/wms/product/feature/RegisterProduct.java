package com.kjh.wms.product.feature;

import com.kjh.wms.product.domain.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterProduct {

    private final ProductRepository productRepository;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody final Request request) {
        validateProductCodeExists(request.code());
        Product product = request.toDomain();
        productRepository.save(product);
    }

    private void validateProductCodeExists(final String code) {
        if (productRepository.existsByCode(code)) {
            throw new IllegalArgumentException("이미 존재하는 상품 코드입니다 %s".formatted(code));
        }
    }

    public record Request(@NotBlank(message = "상품명은 필수입니다")
                          String name,
                          @NotBlank(message = "상품 코드는 필수입니다")
                          String code,
                          @NotBlank(message = "상품 설명은 필수입니다")
                          String description,
                          @NotBlank(message = "브랜드는 필수입니다")
                          String brand,
                          @NotBlank(message = "제조사는 필수입니다")
                          String maker,
                          @NotBlank(message = "원산지는 필수입니다")
                          String origin,
                          @NotBlank(message = "카테고리는 필수입니다")
                          Category electronics,
                          @NotBlank(message = "온도대는 필수입니다")
                          TemperatureZone roomTemperature,
                          @NotBlank(message = "상품 무게는 필수입니다")
                          @Min(value = 0, message = "상품 무게는 0보다 커야 합니다")
                          Long weightInGrams,
                          @NotBlank(message = "상품 너비는 필수입니다")
                          @Min(value = 0, message = "상품 너비는 0보다 커야 합니다")
                          Long widthInMillimeters,
                          @NotBlank(message = "상품 높이는 필수입니다")
                          @Min(value = 0, message = "상품 높이는 0보다 커야 합니다")
                          Long heightInMillimeters,

                          @NotBlank(message = "상품 길이는 필수입니다")
                          @Min(value = 0, message = "상품 길이는 0보다 커야 합니다")
                          Long lengthInMillimeters) {

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
