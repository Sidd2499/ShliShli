package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class MyCartItem {

    @SerializedName("product")
    private Product product;

    @SerializedName("merchant")
    private Merchant merchant;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private Double price;

    @SerializedName("inventory")
    private InventoryItem inventoryItem;

    @SerializedName("cartId")
    private Long cartId;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
