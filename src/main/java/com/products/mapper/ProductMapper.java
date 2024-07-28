package com.products.mapper;

import com.products.controller.model.CreateProductDTO;
import com.products.controller.model.ProductDTO;
import com.products.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

    public Product toProduct(CreateProductDTO createProductDTO) {
        return Product.builder()
                .name(createProductDTO.getName())
                .price(createProductDTO.getPrice())
                .build();
    }

    public ProductDTO toProductDTO(Product product) {

        return ProductDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .id(product.getId())
                .build();
    }

    public List<ProductDTO> toProductDTOs(List<Product> products) {

        return products.stream()
                .map(this::toProductDTO)
                .toList();
    }
}
