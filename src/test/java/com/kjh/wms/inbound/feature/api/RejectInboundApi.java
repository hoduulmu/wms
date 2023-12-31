package com.kjh.wms.inbound.feature.api;

import com.kjh.wms.inbound.feature.RejectInbound;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class RejectInboundApi {

    private Long inboundNo = 1L;
    private String rejectionReason = "반려 사유";

    public RejectInboundApi inboundNo(Long inboundNo) {
        this.inboundNo = inboundNo;
        return this;
    }

    public RejectInboundApi rejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
        return this;
    }

    public void request() {
        final RejectInbound.Request request = new RejectInbound.Request(rejectionReason);
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds/{inboundNo}/reject", inboundNo)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
