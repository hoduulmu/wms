package com.kjh.wms.inbound.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InboundStatus {
    REQUESTED("요청됨"),
    CONFIRMED("승인됨"),
    CANCELED("취소됨")
    ;

    private final String description;
}
