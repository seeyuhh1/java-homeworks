package com.example.order.storage;

import com.example.order.storage.internal.StorageAuditHelper;
import com.example.order.storage.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderStorage {
    private final List<Product> orders = Collections.synchronizedList(new ArrayList<>());

    public void addOrder(Product product) {
        if (product != null) {
            orders.add(product);
            StorageAuditHelper.logStorageAction(product);
        }
    }

    public List<Product> getAllOrders() {
        synchronized (orders) {
            return new ArrayList<>(orders);
        }
    }
}