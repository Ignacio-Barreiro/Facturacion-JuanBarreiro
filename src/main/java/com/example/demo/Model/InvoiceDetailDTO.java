package com.example.demo.Model;
public class InvoiceDetailDTO {
    private Long id;
    private Long invoiceId;
    private Long productId;
    private Double precio;
    private Integer cantidad;

    public InvoiceDetailDTO() {
    }

    public InvoiceDetailDTO(Long id, Long invoiceId, Long productId, Double precio, Integer cantidad) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

