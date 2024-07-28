package com.products.mapper;

import com.products.entity.Product;
import com.products.model.CreateProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(CreateProductDTO createProductDTO) {
        return Product.builder()
                .name(createProductDTO.getName())
                .price(createProductDTO.getPrice())
                .build();
    }
}
