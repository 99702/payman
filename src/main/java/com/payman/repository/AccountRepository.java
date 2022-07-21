package com.payman.repository;

import com.payman.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select * from account a where a.account_number=:accNo", nativeQuery = true)
    Account fetchByAccountNo(String accNo);

    @Query(value = "select * from account a where a.fk_customer=:cId", nativeQuery = true)
    Account fetchByCustomerId(Long cId);
}