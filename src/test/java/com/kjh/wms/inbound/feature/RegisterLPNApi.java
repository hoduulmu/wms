package com.kjh.wms.inbound.feature;

import com.kjh.wms.common.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterLPNApi {

    private Long inboundItemNo = 1L;
    private String lpnBarcode = "LPN-0001";
    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

    public RegisterLPNApi inboundItemNo(Long inboundItemNo) {
        this.inboundItemNo = inboundItemNo;
        return this;
    }

    public RegisterLPNApi lpnBarcode(String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public RegisterLPNApi expirationAt(LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public Scenario request() {
        RegisterLPN.Request request = new RegisterLPN.Request(
                lpnBarcode,
                expirationAt
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds/inbound-items/{inboundItemNo}/lpns", inboundItemNo)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}
