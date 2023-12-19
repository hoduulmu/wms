package com.kjh.wms.inbound.feature;

public class LPNBarcodeAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "이미 존재하는 LPN 바코드입니다. %s";

    public LPNBarcodeAlreadyExistsException(String lpnBarcode) {
        super(MESSAGE.formatted(lpnBarcode));
    }
}
