package com.example.order.threading;

import com.example.order.processing.OrderProcessor;
import com.example.order.storage.model.Clothing;
import com.example.order.storage.model.Electronics;
import com.example.order.storage.model.Product;

import java.util.List;

public class OrderThreadManager {

    public void processAllOrdersInThreads(List<Product> allOrders) {
        OrderProcessor<Electronics> electronicsProcessor = new OrderProcessor<>(Electronics.class);
        OrderProcessor<Clothing> clothingProcessor = new OrderProcessor<>(Clothing.class);

        // Лямбда-вираз для фільтрації замовлень за типом Electronics
        List<Electronics> electronicsOrders = allOrders.stream()
                .filter(product -> product instanceof Electronics)
                .map(product -> (Electronics) product)
                .toList();

        // Лямбда-вираз для фільтрації замовлень за типом Clothing
        List<Clothing> clothingOrders = allOrders.stream()
                .filter(product -> product instanceof Clothing)
                .map(product -> (Clothing) product)
                .toList();

        // Лямбда-вирази для створення потоків та посилання на методи (::) для обробки
        Runnable electronicsTask = () -> {
            System.out.println("Запуск потоку для електроніки: " + Thread.currentThread().getName());
            electronicsOrders.forEach(electronicsProcessor::processOrder);
        };

        Runnable clothingTask = () -> {
            System.out.println("Запуск потоку для одягу: " + Thread.currentThread().getName());
            clothingOrders.forEach(clothingProcessor::processOrder);
        };

        Thread electronicsThread = new Thread(electronicsTask, "Electronics-Thread");
        Thread clothingThread = new Thread(clothingTask, "Clothing-Thread");

        electronicsThread.start();
        clothingThread.start();

        try {
            electronicsThread.join();
            clothingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Головний потік очікування перервано.");
        }

        System.out.println("\n--- Формування чеків (статичне посилання на метод) ---");
        allOrders.forEach(OrderProcessor::printOrderReceipt);
    }
}