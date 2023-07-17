package com.ShopHoa.entity;

public class CartItem {
    public CartItem(Flower flower, int quantity) {
        this.flower = flower;
        this.quantity = quantity;
    }

    public Flower getFlower() {
        return flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private Flower flower;
    private int quantity;
}
