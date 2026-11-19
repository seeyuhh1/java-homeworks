package com.example.order.threading;

import com.example.order.processing.OrderProcessingException;
import com.example.order.processing.OrderProcessor;
import com.example.order.storage.OrderStorage;
import com.example.order.storage.model.Clothing;
import com.example.order.storage.model.Electronics;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ENGLISH);
        OrderStorage storage = new OrderStorage();

        String[] sizes = {"S", "M", "L", "XL"};

        System.out.println("=== 1. Генерація та збереження замовлень (JavaFaker + Lombok @Builder) ===");
        for (int i = 0; i < 3; i++) {
            Electronics electronics = Electronics.builder()
                    .id(UUID.randomUUID().toString().substring(0, 8))
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price(500.0, 25000.0).replace(",", ".")))
                    .brand(faker.company().name())
                    .warrantyMonths(faker.number().numberBetween(12, 36))
                    .build();
            storage.addOrder(electronics);

            Clothing clothing = Clothing.builder()
                    .id(UUID.randomUUID().toString().substring(0, 8))
                    .name(faker.commerce().material() + " Jacket")
                    .price(Double.parseDouble(faker.commerce().price(300.0, 5000.0).replace(",", ".")))
                    .size(sizes[faker.random().nextInt(sizes.length)])
                    .material(faker.commerce().material())
                    .build();
            storage.addOrder(clothing);
        }

        System.out.println("\n=== 2. Паралельна обробка замовлень у різних потоках ===");
        OrderThreadManager threadManager = new OrderThreadManager();
        threadManager.processAllOrdersInThreads(storage.getAllOrders());

        System.out.println("\n=== 3. Перевірка обробки виключення для узагальнених типів ===");
        OrderProcessor<Electronics> strictProcessor = new OrderProcessor<>(Electronics.class);
        Electronics invalidProduct = Electronics.builder()
                .id("ERR-01")
                .name("Broken Laptop")
                .price(-100.0)
                .brand("TestBrand")
                .warrantyMonths(0)
                .build();

        try {
            strictProcessor.validateGenericOrder(invalidProduct);
        } catch (OrderProcessingException e) {
            System.out.println("Успішно перехоплено виключення: " + e.getMessage());
        }
    }
}