package com.products.service;

import com.products.entity.Product;
import com.products.exception.ProductNotFoundException;
import com.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product create(Product product) {

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updatePrice(Long id, BigDecimal price) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setPrice(price);
            return productRepository.save(product.get());
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }
}
