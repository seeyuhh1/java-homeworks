module order.processing {
    requires transitive order.storage;
    requires static lombok;

    exports com.example.order.processing;
    // Пакет com.example.order.processing.internal прихований від інших модулів
}