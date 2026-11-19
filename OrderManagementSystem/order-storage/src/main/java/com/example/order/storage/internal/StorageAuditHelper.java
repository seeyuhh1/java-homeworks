package com.example.order.storage.internal;

import com.example.order.storage.model.Product;

public class StorageAuditHelper {
    public static void logStorageAction(Product product) {
        System.out.println("[Storage Audit] Збережено товар: " + product.getName() + " (ID: " + product.getId() + ")");
    }
}