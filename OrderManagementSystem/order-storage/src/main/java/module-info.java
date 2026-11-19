module order.storage {
    requires static lombok;

    exports com.example.order.storage;
    exports com.example.order.storage.model;
    // Пакет com.example.order.storage.internal НЕ експортується — доступ з інших модулів закрито
}