package com.nickolas.productmanagement.services;

import com.nickolas.productmanagement.entities.Product;
import com.nickolas.productmanagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


    public Page<Product> getProductsWithPagination(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getProductsWithPaginationAndPriceSorting(int page, int size, String priceOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        if ("desc".equals(priceOrder)) {
            pageable = PageRequest.of(page, size, Sort.by("price").descending());
        }
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategoryWithPagination(int page, int size, Long categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Product> getFilteredProducts(
            int page,
            int size,
            Long categoryId,
            String priceOrder,
            Double minPrice,
            Double maxPrice) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        if ("desc".equalsIgnoreCase(priceOrder)) {
            pageable = PageRequest.of(page, size, Sort.by("price").descending());
        }

        if (categoryId != null && minPrice != null && maxPrice != null) {
            return productRepository.findByCategoryIdAndPriceBetween(categoryId, minPrice, maxPrice, pageable);
        } else if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId, pageable);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }
}
