package com.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Каталог товарів
        List<Product> storeProducts = new ArrayList<>();
        storeProducts.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics));
        storeProducts.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones));
        storeProducts.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories));

        while (true) {
            System.out.println("\n--- ІНТЕРНЕТ МАГАЗИН ---");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Пошук товару (за назвою або категорією)");
            System.out.println("3 - Додати товар до кошика");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Переглянути кошик");
            System.out.println("6 - Зробити замовлення");
            System.out.println("7 - Історія замовлень");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Будь ласка, введіть число.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nКаталог товарів:");
                    for (Product p : storeProducts) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Введіть назву або категорію для пошуку: ");
                    String searchQuery = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    System.out.println("\nРезультати пошуку:");
                    for (Product p : storeProducts) {
                        if (p.getName().toLowerCase().contains(searchQuery) ||
                            p.getCategory().getName().toLowerCase().contains(searchQuery)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }
                    break;

                case 3:
                    System.out.print("Введіть ID товару для додавання до кошика: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Некоректний ID.");
                        scanner.next();
                        break;
                    }
                    int addId = scanner.nextInt();
                    scanner.nextLine();

                    Product productToAdd = null;
                    for (Product p : storeProducts) {
                        if (p.getId() == addId) {
                            productToAdd = p;
                            break;
                        }
                    }

                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар \"" + productToAdd.getName() + "\" додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 4:
                    System.out.print("Введіть ID товару для видалення з кошика: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Некоректний ID.");
                        scanner.next();
                        break;
                    }
                    int removeId = scanner.nextInt();
                    scanner.nextLine();

                    if (cart.removeProductById(removeId)) {
                        System.out.println("Товар успішно видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID відсутній у кошику.");
                    }
                    break;

                case 5:
                    System.out.println("\n" + cart);
                    break;

                case 6:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("\nЗамовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 7:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("\n--- Історія замовлень ---");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("Замовлення #" + (i + 1) + ":");
                            System.out.println(orderHistory.get(i));
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}