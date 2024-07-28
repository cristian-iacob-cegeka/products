package com.products.controller;

import com.products.controller.model.CreateProductDTO;
import com.products.controller.model.ProductDTO;
import com.products.entity.Product;
import com.products.mapper.ProductMapper;
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
    public ResponseEntity<ProductDTO> create(@RequestBody CreateProductDTO createProductDTO) {

        Product product = productMapper.toProduct(createProductDTO);
        Product createdProduct = productService.create(product);
        ProductDTO productDTO = productMapper.toProductDTO(createdProduct);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {

        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOs = productMapper.toProductDTOs(products);
        return ResponseEntity.ok(productDTOs);
    }

    @PatchMapping(value = "/{id}/update-price/{price}")
    public ResponseEntity<ProductDTO> updatePrice(@PathVariable Long id,
                                                  @PathVariable BigDecimal price) {

        Product product = productService.updatePrice(id, price);
        ProductDTO productDTO = productMapper.toProductDTO(product);

        return ResponseEntity.ok(productDTO);
    }
}
