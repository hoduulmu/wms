package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RejectInbound {

    private final InboundRepository inboundRepository;

    @Transactional
    @PostMapping("/inbounds/{inboundNo}/reject")
    public void request(@PathVariable final Long inboundNo,
                        @RequestBody final Request request) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);
        inbound.reject(request.rejectionReason);
    }

    public record Request(@NotBlank(message = "거절 사유는 필수입니다")
                          String rejectionReason) {
    }
}
