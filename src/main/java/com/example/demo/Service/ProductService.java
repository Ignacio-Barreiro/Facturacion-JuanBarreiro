package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Model.ProductDTO;
import com.example.demo.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return mapToDTOList(products);
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return mapToDTO(product);
        }
        return null;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product createdProduct = productRepository.save(product);
        return mapToDTO(createdProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setTitulo(productDTO.getTitulo());
            product.setDescripcion(productDTO.getDescripcion());
            product.setStock(productDTO.getStock());
            product.setPrecio(productDTO.getPrecio());
            product.setCodigoInterno(productDTO.getCodigoInterno());
            Product updatedProduct = productRepository.save(product);
            return mapToDTO(updatedProduct);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return true;
        }
        return false;
    }

    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getTitulo(), product.getDescripcion(),
                product.getStock(), product.getPrecio(), product.getCodigoInterno());
    }

    private List<ProductDTO> mapToDTOList(List<Product> products) {
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getTitulo(), productDTO.getDescripcion(), productDTO.getStock(),
                productDTO.getPrecio(), productDTO.getCodigoInterno());
    }
}
