package com.example.demo.Model;


public class InvoiceDetailRequest {
    private Long productId;
    private Integer quantity;

    public InvoiceDetailRequest() {
    }

    public InvoiceDetailRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters y Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

