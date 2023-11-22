package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;

class RejectInbound {

    private final InboundRepository inboundRepository;

    RejectInbound(InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    public void request(Long inboundNo, Request request) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);
        inbound.reject(request.rejectionReason);
    }

    public record Request(String rejectionReason) {
    }
}
