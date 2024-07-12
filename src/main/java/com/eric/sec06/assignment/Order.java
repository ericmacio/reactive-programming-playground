package com.eric.sec06.assignment;

public class Order {

    private String item;
    private String category;
    private int price;
    private int quantity;

    public Order(String item, String category, int price, int quantity) {
        this.item = item;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
