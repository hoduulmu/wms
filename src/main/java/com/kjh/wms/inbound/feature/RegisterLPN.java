package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Component
public class RegisterLPN {

    private final InboundRepository inboundRepository;

    RegisterLPN(InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    @Transactional
    public void request(Request request) {
        final Inbound inbound = inboundRepository.getByInboundItemNo(request.inboundItemNo());
        inbound.registerLPN(request.inboundItemNo(), request.lpnBarcode(), request.expirationAt());
    }

    public record Request(Long inboundItemNo,
                          String lpnBarcode,
                          LocalDateTime expirationAt) {
        public Request {
            Assert.notNull(inboundItemNo, "LPN 등록할 입고 상품 번호는 필수입니다.");
            Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
            Assert.notNull(expirationAt, "유통기한은 필수입니다.");
        }
    }
}
