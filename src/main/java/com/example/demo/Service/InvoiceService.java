package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientService clientService;
    private final ProductService productService;

    public InvoiceService(InvoiceRepository invoiceRepository, ClientService clientService, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public InvoiceDTO getInvoiceById(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            return mapToDTO(invoice);
        }
        return null;
    }

    public InvoiceDTO createInvoice(InvoiceRequest invoiceRequest) {
        Long clientId = invoiceRequest.getClientId();
        ClientDTO clientDTO = clientService.getClientById(clientId);
        if (clientDTO == null) {
            throw new IllegalArgumentException("El cliente no existe.");
        }

        // Validar existencia y stock de los productos
        List<InvoiceDetailRequest> detailRequests = invoiceRequest.getProductList();
        List<InvoiceDetail> details = new ArrayList<>();
        for (InvoiceDetailRequest detailRequest : detailRequests) {
            Long productId = detailRequest.getProductId();
            ProductDTO productDTO = productService.getProductById(productId);
            if (productDTO == null) {
                throw new IllegalArgumentException("El producto con ID " + productId + " no existe.");
            }
            int quantity = detailRequest.getQuantity();
            if (quantity > productDTO.getStock()) {
                throw new IllegalArgumentException("No hay suficiente stock del producto con ID " + productId);
            }

            InvoiceDetail detail = new InvoiceDetail();
            detail.setProduct(mapToProductEntity(productDTO));
            detail.setPrecio(productDTO.getPrecio());
            detail.setCantidad(quantity);
            details.add(detail);

            // Actualizar stock del producto
            productDTO.setStock(productDTO.getStock() - quantity);
            productService.updateProduct(productId, productDTO);
        }

        // Crear la factura
        Invoice invoice = new Invoice();
        invoice.setClient(mapToClientEntity(clientDTO));
        invoice.setDetails(details);
        Invoice createdInvoice = invoiceRepository.save(invoice);
        return mapToDTO(createdInvoice);
    }

    public boolean deleteInvoice(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            invoiceRepository.delete(invoiceOptional.get());
            return true;
        }
        return false;
    }

    private InvoiceDTO mapToDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setClientId(invoice.getClient().getId());
        invoiceDTO.setDetails(mapToDetailDTOList(invoice.getDetails()));
        return invoiceDTO;
    }

    private List<InvoiceDetailDTO> mapToDetailDTOList(List<InvoiceDetail> details) {
        return details.stream()
                .map(this::mapToDetailDTO)
                .collect(Collectors.toList());
    }

    private InvoiceDetailDTO mapToDetailDTO(InvoiceDetail detail) {
        InvoiceDetailDTO detailDTO = new InvoiceDetailDTO();
        detailDTO.setId(detail.getId());
        detailDTO.setInvoiceId(detail.getInvoice().getId());
        detailDTO.setProductId(detail.getProduct().getId());
        detailDTO.setPrecio(detail.getPrecio());
        detailDTO.setCantidad(detail.getCantidad());
        return detailDTO;
    }

    private Client mapToClientEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setNombre(clientDTO.getNombre());
        client.setApellido(clientDTO.getApellido());
        client.setNumeroDocumento(clientDTO.getNumeroDocumento());
        return client;
    }

    private Product mapToProductEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitulo(productDTO.getTitulo());
        product.setDescripcion(productDTO.getDescripcion());
        product.setStock(productDTO.getStock());
        product.setPrecio(productDTO.getPrecio());
        product.setCodigoInterno(productDTO.getCodigoInterno());
        return product;
    }
}

