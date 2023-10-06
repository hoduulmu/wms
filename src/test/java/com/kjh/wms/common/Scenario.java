package com.kjh.wms.common;

import com.kjh.wms.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }
}
