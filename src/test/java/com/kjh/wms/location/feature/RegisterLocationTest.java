package com.kjh.wms.location.feature;

import com.kjh.wms.common.ApiTest;
import com.kjh.wms.location.domain.LocationRepository;
import com.kjh.wms.location.domain.StorageType;
import com.kjh.wms.location.domain.UsagePurpose;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLocationTest extends ApiTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션을 등록한다")
    void registerLocation() {

        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;

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

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
