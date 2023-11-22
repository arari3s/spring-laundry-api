package com.example.laundry.repository;

import com.example.laundry.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM m_product WHERE id = :id", nativeQuery = true)
    Optional<Product> getProductById(@Param("id") String id);

    @Query(value = "SELECT * FROM m_product", nativeQuery = true)
    List<Product> getAllProduct();
}
