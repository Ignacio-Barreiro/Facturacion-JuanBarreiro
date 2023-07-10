package com.example.demo.Model;

import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Long clientId;
    private List<InvoiceDetailDTO> details;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, Long clientId, List<InvoiceDetailDTO> details) {
        this.id = id;
        this.clientId = clientId;
        this.details = details;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<InvoiceDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetailDTO> details) {
        this.details = details;
    }
}

