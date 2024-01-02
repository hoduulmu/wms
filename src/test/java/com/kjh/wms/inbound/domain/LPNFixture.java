package com.kjh.wms.inbound.domain;

import java.time.LocalDateTime;

public class LPNFixture {


    private String lpnBarcode = "LPN-1";
    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
    private InboundItem inboundItem = InboundItemFixture.anInboundItem().build();

    public static LPNFixture anLPN() {
        return new LPNFixture();
    }

    public LPNFixture lpnBarcode(String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public LPNFixture expirationAt(LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public LPNFixture inboundItem(InboundItem inboundItem) {
        this.inboundItem = inboundItem;
        return this;
    }

    public LPN build() {
        return new LPN(lpnBarcode, expirationAt, inboundItem);
    }
}
