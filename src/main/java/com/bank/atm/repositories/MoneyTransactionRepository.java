package com.bank.atm.repositories;

import com.bank.atm.repositories.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyTransactionRepository extends JpaRepository<Card, Long> {

    @Query(value = "select Balance from card where Card_No = :cardNo", nativeQuery = true)
    Double findBalanceUsingByCardNo(@Param("cardNo") String cardNo);

    @Query(value = "select * from card where Card_No = :cardNo", nativeQuery = true)
    Card findByCardNo(@Param("cardNo") String cardNo);

    @Query(value = "select count(*) from card where Card_No = :cardNo", nativeQuery = true)
    int findByCardNoCount(@Param("cardNo") String cardNo);

}
