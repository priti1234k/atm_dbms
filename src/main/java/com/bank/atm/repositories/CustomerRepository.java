package com.bank.atm.repositories;

import com.bank.atm.repositories.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(value = "Select * from Customer where id = :customerId", nativeQuery = true)
    Customer findByCustomerId(@Param("customerId") Long userId);
}
