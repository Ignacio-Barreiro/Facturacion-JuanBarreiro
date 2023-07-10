package com.example.demo.Model;

import java.util.List;

public class InvoiceRequest {
    private Long clientId;
    private List<InvoiceDetailRequest> productList;

    public InvoiceRequest() {
    }
    public InvoiceRequest(Long clientId, List<InvoiceDetailRequest> productList) {
        this.clientId = clientId;
        this.productList = productList;
    }

    // Getters y Setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<InvoiceDetailRequest> getProductList() {
        return productList;
    }

    public void setProductList(List<InvoiceDetailRequest> productList) {
        this.productList = productList;
    }
}

