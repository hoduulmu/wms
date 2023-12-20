package com.kjh.wms.location.feature;

import org.springframework.util.Assert;

class Location {

    private Long locationNo;
    private final String locationBarcode;
    private final StorageType storageType;
    private final UsagePurpose usagePurpose;

    public Location(String locationBarcode,
                    StorageType storageType,
                    UsagePurpose usagePurpose) {
        validateConstructor(locationBarcode, storageType, usagePurpose);
        this.locationBarcode = locationBarcode;
        this.storageType = storageType;
        this.usagePurpose = usagePurpose;
    }

    private void validateConstructor(String locationBarcode,
                                     StorageType storageType,
                                     UsagePurpose usagePurpose) {
        Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다");
        Assert.notNull(storageType, "로케이션 유형은 필수입니다");
        Assert.notNull(usagePurpose, "로케이션 용도는 필수입니다");
    }

    public void assignNo(Long locationNo) {
        this.locationNo = locationNo;
    }

    public Long getLocationNo() {
        return locationNo;
    }
}
