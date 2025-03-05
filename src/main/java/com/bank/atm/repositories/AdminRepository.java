package com.bank.atm.repositories;

import com.bank.atm.repositories.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "select  id from admin where username = :userName and Password = :password", nativeQuery = true)
    Long findIdByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    boolean existsById(Long Id);
}
