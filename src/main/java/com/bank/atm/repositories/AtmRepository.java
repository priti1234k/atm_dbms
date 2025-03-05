package com.bank.atm.repositories;


import com.bank.atm.repositories.entities.Atm;
import com.bank.atm.repositories.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtmRepository  extends JpaRepository<Atm, Long> {

    @Query(value = "Select * from Atm where bank_id = :bankId", nativeQuery = true)
    List<Atm> findAllAtm(@Param("bankId") Long id);

    @Query(value = "Select count(*) from Atm where bank_id = :bankId", nativeQuery = true)
    int findByBankId(@Param("bankId") Long id);

    @Query(value = "Select balance from Atm ", nativeQuery = true)
    Double findAtmBalance();

//    @Query(value = "update Atm set balance = :balance where atmId = :id", nativeQuery = true)
//    @Modifying
//    void updateBalance(@Param(value = "atmId") long id, @Param(value = "balance") Double balance );

//    @Modifying
//    @Query("Delete from Atm where atm_id=:id")
//    void deleteAtm(@Param("atm_id") Long id);
}
