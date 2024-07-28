package com.products.service;

import com.products.entity.Product;
import com.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product create(Product product) {

        return productRepository.save(product);
    }
}
