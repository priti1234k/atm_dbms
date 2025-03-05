package com.bank.atm.repositories;

import com.bank.atm.repositories.entities.Bank;
import com.bank.atm.repositories.entities.Card;
import com.bank.atm.repositories.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "select Card_No from card where Card_No = :cardNo and Pin = :pin", nativeQuery = true)
    String findIdByCardNoAndPin(@Param("cardNo") String cardNo, @Param("pin") String pin);

    @Query(value = "select Balance from card where Card_No = :cardNo", nativeQuery = true)
    Card findBalanceByCardNo(@Param("cardNo") String cardNo);
}
