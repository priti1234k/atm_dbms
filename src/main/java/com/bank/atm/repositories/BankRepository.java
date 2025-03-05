package com.bank.atm.repositories;


import com.bank.atm.repositories.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query(value = "Select * from Bank", nativeQuery = true)
    List<Bank> findAllBanks();

    @Query(value = "Select * from Bank where IFSC_Code = :ifscCode", nativeQuery = true)
    Bank findBankIfsc(@Param("ifscCode") String ifscCode);

//    @Modifying
//    @Query("Delete from Bank where bank_id=:id")
//    void deleteBooks(@Param("bank_id") Long id);

    boolean existsById(Long Id);

}
