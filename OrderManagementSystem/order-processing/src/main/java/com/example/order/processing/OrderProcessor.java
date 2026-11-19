package com.example.order.processing;

import com.example.order.processing.internal.InternalPriceValidator;
import com.example.order.storage.model.Product;

public class OrderProcessor<T extends Product> {
    private final Class<T> supportedType;

    public OrderProcessor(Class<T> supportedType) {
        this.supportedType = supportedType;
    }

    public void processOrder(T product) {
        try {
            validateGenericOrder(product);
            String threadName = Thread.currentThread().getName();
            System.out.printf("[%s] Обробка замовлення (%s): %s | Ціна: %.2f грн%n",
                    threadName,
                    supportedType.getSimpleName(),
                    product.getName(),
                    product.getPrice());
            Thread.sleep(300);
        } catch (OrderProcessingException e) {
            System.err.println("Помилка обробки узагальненого замовлення: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Потік обробки було перервано.");
        }
    }

    public void validateGenericOrder(T product) throws OrderProcessingException {
        if (product == null) {
            throw new OrderProcessingException("Об'єкт замовлення не може бути null.");
        }
        if (!supportedType.isInstance(product)) {
            throw new OrderProcessingException("Несумісний тип товару! Очікувався: "
                    + supportedType.getSimpleName() + ", отримано: " + product.getClass().getSimpleName());
        }
        if (!InternalPriceValidator.isValid(product)) {
            throw new OrderProcessingException("Некоректні дані або ціна товару: " + product.getName());
        }
    }

    public static void printOrderReceipt(Product product) {
        System.out.println("Чек сформовано для: " + product.getName() + " — " + product.getPrice() + " грн");
    }
}