package com.kjh.wms.location.domain;

import com.kjh.wms.inbound.domain.LPN;
import lombok.Getter;

public class LocationLPN {

    private Location location;

    @Getter
    private LPN lpn;
    private Long inventoryQuantity;

    public LocationLPN(Location location, LPN lpn) {
        this.location = location;
        this.lpn = lpn;
        this.inventoryQuantity = 1L;
    }

    public void increaseInventoryQuantity() {
        inventoryQuantity++;
    }
}
