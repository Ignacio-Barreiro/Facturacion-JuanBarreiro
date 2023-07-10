package com.example.demo.Model;
public class ProductDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer stock;
    private Double precio;
    private String codigoInterno;
    public ProductDTO() {
    }
    public ProductDTO(Long id, String titulo, String descripcion, Integer stock, Double precio, String codigoInterno) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.codigoInterno = codigoInterno;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getCodigoInterno() {
        return codigoInterno;
    }
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }
}

