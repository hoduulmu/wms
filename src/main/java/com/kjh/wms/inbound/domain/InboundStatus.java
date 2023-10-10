package com.kjh.wms.inbound.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InboundStatus {
    REQUESTED("요청됨"),
    CONFIRMED("승인됨"),
    REJECTED("거부됨")
    ;

    private final String description;
}
