package com.kjh.wms.inbound.feature.api;

import com.kjh.wms.common.Scenario;
import com.kjh.wms.inbound.feature.RegisterInbound;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterInboundApi {

    private String title = "title";
    private String description1 = "description";
    private LocalDateTime orderRequestedAt = LocalDateTime.now();
    private LocalDateTime estimateArrivalAt = LocalDateTime.now().plusDays(1);
    private List<RegisterInbound.Request.Item> inboundItems = List.of(
            new RegisterInbound.Request.Item(
                    1L,
                    1L,
                    1500L,
                    "description"
            ));

    public RegisterInboundApi title(String title) {
        this.title = title;
        return this;
    }

    public RegisterInboundApi description(String description) {
        this.description1 = description;
        return this;
    }

    public RegisterInboundApi orderRequestedAt(LocalDateTime orderRequestedAt) {
        this.orderRequestedAt = orderRequestedAt;
        return this;
    }

    public RegisterInboundApi estimateArrivalAt(LocalDateTime estimateArrivalAt) {
        this.estimateArrivalAt = estimateArrivalAt;
        return this;
    }

    public RegisterInboundApi inboundItems(RegisterInbound.Request.Item... inboundItems) {
        this.inboundItems = List.of(inboundItems);
        return this;
    }


    public Scenario request() {
        final RegisterInbound.Request request = new RegisterInbound.Request(
                title,
                description1,
                orderRequestedAt,
                estimateArrivalAt,
                inboundItems
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
