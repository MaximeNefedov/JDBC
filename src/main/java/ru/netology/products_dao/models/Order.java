package ru.netology.products_dao.models;

public class Order {
    private final String productName;
    private final int amount;
    private final String date;

    public Order(String productName, int amount, String date) {
        this.productName = productName;
        this.amount = amount;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Заказ: " + productName + ", " + amount + " шт., " + "дата: " + date;
    }
}
