module order.threading {
    requires order.storage;
    requires order.processing;
    requires javafaker;
    requires java.sql;
    requires java.desktop;
    requires static lombok;

    exports com.example.order.threading;
}