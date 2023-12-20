package com.kjh.wms.common;

import com.kjh.wms.inbound.feature.RegisterLPNApi;
import com.kjh.wms.inbound.feature.api.ConfirmInboundApi;
import com.kjh.wms.inbound.feature.api.RegisterInboundApi;
import com.kjh.wms.inbound.feature.api.RejectInboundApi;
import com.kjh.wms.location.feature.api.RegisterLocationApi;
import com.kjh.wms.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }

    public static RegisterLocationApi registerLocation() {
        return new RegisterLocationApi();
    }

    public RegisterInboundApi registerInbound() {
        return new RegisterInboundApi();
    }

    public ConfirmInboundApi confirmInbound() {
        return new ConfirmInboundApi();
    }

    public RejectInboundApi rejectInbound() {
        return new RejectInboundApi();
    }

    public RegisterLPNApi registerLPN() {
        return new RegisterLPNApi();
    }
}
