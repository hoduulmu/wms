package com.kjh.wms.inbound.feature;

import com.kjh.wms.common.ApiTest;
import com.kjh.wms.common.Scenario;
import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundItem;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.inbound.domain.LPN;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLPNTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @Transactional
    @DisplayName("LPN 등록")
    void registerLPN() {
        Scenario
                .registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request();

        Long inboundItemNo = 1L;
        String lpnBarcode = "LPN-0001";
        LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
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

        // then
        Inbound inbound = inboundRepository.getByInboundItemNo(inboundItemNo);
        InboundItem inboundItem = inbound.testingGetInboundItemBy(inboundItemNo);
        List<LPN> lpnList = inboundItem.testingGetLpnList();
        assertThat(lpnList).hasSize(1);
    }
}
