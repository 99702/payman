package com.payman.repository;

import com.payman.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query(value = "select * from balance b where b.fk_account=:aId", nativeQuery = true)
    Balance fetchByAccountId(Long aId);

    @Query(value = "select b.* from balance as b inner join account as a on b.fk_account = a.id INNER JOIN customer c on a.fk_customer=c.id where c.id=:cId", nativeQuery = true)
//    @Query(value = "select b from Balance b inner join  Account a on b.account=a.accountNumber")
    Balance fetchBalanceByCustomerId(Long cId);
}