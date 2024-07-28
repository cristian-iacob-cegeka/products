package com.products.controller;

import com.products.entity.Product;
import com.products.mapper.ProductMapper;
import com.products.model.CreateProductDTO;
import com.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody CreateProductDTO createProductDTO) {

        Product product = productMapper.toProduct(createProductDTO);
        Product createdProduct = productService.create(product);

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {

        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PatchMapping(value = "/{id}/update-price/{price}")
    public ResponseEntity<Product> updatePrice(@PathVariable Long id,
                                               @PathVariable BigDecimal price) {

        Product product = productService.updatePrice(id, price);
        return ResponseEntity.ok(product);
    }
}
