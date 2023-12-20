package com.kjh.wms.location.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterLocationTest {


    private RegisterLocation registerLocation;

    @BeforeEach
    void setUp() {
        registerLocation = new RegisterLocation();
    }

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
        registerLocation.request();
    }

    private class RegisterLocation {

        public void request() {

        }

        public record Request(String locationBarcode,
                              StorageType storageType,
                              UsagePurpose usagePurpose) {
        }
    }

    enum StorageType {
        TOTE("토트 바구니")
        ;

        private final String description;

        StorageType(String description) {
            this.description = description;
        }
    }

    enum UsagePurpose {
        MOVE("이동 목적")
        ;

        private final String description;

        UsagePurpose(String description) {
            this.description = description;
        }
    }
}
