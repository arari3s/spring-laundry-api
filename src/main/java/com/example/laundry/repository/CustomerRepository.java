package com.example.laundry.repository;

import com.example.laundry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT * FROM m_customer WHERE id = :id", nativeQuery = true)
    Optional<Customer> getCustomerById(@Param("id") String id);

    @Query(value = "SELECT * FROM m_customer", nativeQuery = true)
    List<Customer> getAllCustomer();
}
