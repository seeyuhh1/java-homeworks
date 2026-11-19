package com.example.order.processing.internal;

import com.example.order.storage.model.Product;

public class InternalPriceValidator {
    public static boolean isValid(Product product) {
        return product != null && product.getPrice() > 0 && product.getName() != null && !product.getName().isBlank();
    }
}