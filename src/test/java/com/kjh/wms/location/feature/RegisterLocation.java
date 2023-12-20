package com.kjh.wms.location.feature;

import org.springframework.util.Assert;

class RegisterLocation {

    private final LocationRepository locationRepository;

    RegisterLocation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void request(Request request) {
        Location location = request.toDomain();
        locationRepository.save(location);
    }

    public record Request(String locationBarcode,
                          StorageType storageType,
                          UsagePurpose usagePurpose) {

        public Request {
            Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다");
            Assert.notNull(storageType, "로케이션 유형은 필수입니다");
            Assert.notNull(usagePurpose, "로케이션 용도는 필수입니다");
        }

        public Location toDomain() {
            return new Location(locationBarcode, storageType, usagePurpose);
        }
    }
}
