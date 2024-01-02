package com.kjh.wms.location.domain;

public class LocationFixture {

    private String locationBarcode = "A-1-1";
    private StorageType storageType = StorageType.TOTE;
    private UsagePurpose usagePurpose = UsagePurpose.MOVE;

    public static LocationFixture aLocation() {
        return new LocationFixture();
    }

    public LocationFixture locationBarcode(String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public LocationFixture storageType(StorageType storageType) {
        this.storageType = storageType;
        return this;
    }

    public LocationFixture usagePurpose(UsagePurpose usagePurpose) {
        this.usagePurpose = usagePurpose;
        return this;
    }

    public Location build() {
        return new Location(locationBarcode, storageType, usagePurpose);
    }
}
