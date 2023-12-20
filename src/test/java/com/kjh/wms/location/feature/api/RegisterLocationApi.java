package com.kjh.wms.location.feature.api;

import com.kjh.wms.common.Scenario;
import com.kjh.wms.location.domain.StorageType;
import com.kjh.wms.location.domain.UsagePurpose;
import com.kjh.wms.location.feature.RegisterLocation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class RegisterLocationApi {

    private String locationBarcode = "A-1-1";
    private StorageType storageType = StorageType.TOTE;
    private UsagePurpose usagePurpose = UsagePurpose.MOVE;

    public RegisterLocationApi locationBarcode(String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public RegisterLocationApi storageType(StorageType storageType) {
        this.storageType = storageType;
        return this;
    }

    public RegisterLocationApi usagePurpose(UsagePurpose usagePurpose) {
        this.usagePurpose = usagePurpose;
        return this;
    }

    public Scenario request() {

        RegisterLocation.Request request = new RegisterLocation.Request(
                locationBarcode,
                storageType,
                usagePurpose
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/locations")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
