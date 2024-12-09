package com.nickolas.productmanagement.repositories;

import com.nickolas.productmanagement.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
    Page<Product> findByCategoryIdAndPriceBetween(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE " + "(:name IS NULL OR p.name LIKE %:name%) AND " + "(:minPrice IS NULL OR p.price >= :minPrice) AND " + "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<Product> findAllByFilters(@Param("name") String name, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);
}
