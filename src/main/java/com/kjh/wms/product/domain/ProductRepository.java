package com.kjh.wms.product.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public void save(Product product) {
        product.assignId(nextId++);
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return List.copyOf(products.values());
    }
}
