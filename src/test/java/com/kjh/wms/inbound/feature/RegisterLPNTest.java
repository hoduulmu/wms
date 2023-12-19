package com.kjh.wms.inbound.feature;

import com.kjh.wms.common.ApiTest;
import com.kjh.wms.common.Scenario;
import com.kjh.wms.inbound.domain.Inbound;
import com.kjh.wms.inbound.domain.InboundItem;
import com.kjh.wms.inbound.domain.InboundRepository;
import com.kjh.wms.inbound.domain.LPN;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLPNTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @Transactional
    @DisplayName("LPN 등록")
    void registerLPN() {
        final Long inboundItemNo = 1L;

        Scenario
                .registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request()
                .registerLPN().inboundItemNo(inboundItemNo).request();

        // then
        Inbound inbound = inboundRepository.getByInboundItemNo(inboundItemNo);
        InboundItem inboundItem = inbound.testingGetInboundItemBy(inboundItemNo);
        List<LPN> lpnList = inboundItem.testingGetLpnList();
        assertThat(lpnList).hasSize(1);
    }
}
