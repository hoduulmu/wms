package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;

public class ConfirmInbound {

    private final InboundRepository inboundRepository;

    public ConfirmInbound(InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    public void request(Long inboundNo) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);
        inbound.confirmed();
    }
}
