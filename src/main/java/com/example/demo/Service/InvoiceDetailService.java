package com.example.demo.Service;
import com.example.demo.Model.InvoiceDetail;
import com.example.demo.Model.InvoiceDetailDTO;
import com.example.demo.Repository.InvoiceDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceDetailService {
    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailService(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    public List<InvoiceDetailDTO> getInvoiceDetailsByInvoiceId(Long invoiceId) {
        List<InvoiceDetail> invoiceDetails = invoiceDetailRepository.findByInvoiceId(invoiceId);
        return mapToDTOList(invoiceDetails);
    }

    private List<InvoiceDetailDTO> mapToDTOList(List<InvoiceDetail> invoiceDetails) {
        return invoiceDetails.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private InvoiceDetailDTO mapToDTO(InvoiceDetail invoiceDetail) {
        InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
        invoiceDetailDTO.setId(invoiceDetail.getId());
        invoiceDetailDTO.setInvoiceId(invoiceDetail.getInvoice().getId());
        invoiceDetailDTO.setProductId(invoiceDetail.getProduct().getId());
        invoiceDetailDTO.setPrecio(invoiceDetail.getPrecio());
        invoiceDetailDTO.setCantidad(invoiceDetail.getCantidad());
        return invoiceDetailDTO;
    }
}
