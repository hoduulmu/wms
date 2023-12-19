package com.kjh.wms.inbound.feature;

import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.inbound.domain.LPNRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RegisterLPN {

    private final InboundRepository inboundRepository;
    private final LPNRepository lPNRepository;

    RegisterLPN(InboundRepository inboundRepository,
                LPNRepository lPNRepository) {
        this.inboundRepository = inboundRepository;
        this.lPNRepository = lPNRepository;
    }

    @PostMapping("/inbounds/inbound-items/{inboundItemNo}/lpns")
    @Transactional
    public void request(@PathVariable Long inboundItemNo, @RequestBody @Valid Request request) {
        checkIfLPNBarcodeAlreadyExists(request.lpnBarcode());
        final Inbound inbound = inboundRepository.getByInboundItemNo(inboundItemNo);
        inbound.registerLPN(inboundItemNo, request.lpnBarcode(), request.expirationAt());
    }

    private void checkIfLPNBarcodeAlreadyExists(String lpnBarcode) {
        if (lPNRepository.existsByLpnBarcode(lpnBarcode)) {
            throw new LPNBarcodeAlreadyExistsException(lpnBarcode);
        }
    }

    public record Request(
            @NotBlank(message = "LPN 바코드는 필수입니다.")
            String lpnBarcode,

            @NotNull(message = "유통기한은 필수입니다.")
            LocalDateTime expirationAt) {
    }
}
