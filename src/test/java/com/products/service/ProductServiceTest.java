package com.products.service;

import com.products.entity.Product;
import com.products.exception.ProductNotFoundException;
import com.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void givenProducts_whenFindAll_thenReturnProducts() {

        Product product1 = new Product(1L, "product1", BigDecimal.TEN);
        Product product2 = new Product(2L, "product2", BigDecimal.TEN);
        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        List<Product> actualProducts = productService.findAll();

        assertThat(actualProducts)
                .extracting(
                        Product::getId,
                        Product::getName,
                        Product::getPrice
                )
                .containsExactlyInAnyOrder(
                        tuple(product1.getId(), product1.getName(), product1.getPrice()),
                        tuple(product2.getId(), product2.getName(), product2.getPrice())
                );
    }

    @Test
    void givenInvalidId_whenUpdateProductPrice_thenExceptionIsThrown() {

        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.updatePrice(1L, BigDecimal.TEN));

        assertThat(productNotFoundException.getMessage())
                .isEqualTo("Product not found");
    }

    @Test
    void givenIdAndPice_whenUpdateProductPrice_thenUpdateProductIsReturned() {

        Product product = new Product(1L, "product1", BigDecimal.TEN);
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));
        when(productRepository.save(product))
                .thenReturn(product);

        Product updateProduct = productService.updatePrice(1L, BigDecimal.TWO);

        assertThat(updateProduct.getId())
                .isEqualTo(1L);
        assertThat(updateProduct.getName())
                .isEqualTo("product1");
        assertThat(updateProduct.getPrice())
                .isEqualTo(BigDecimal.TWO);
    }
}
