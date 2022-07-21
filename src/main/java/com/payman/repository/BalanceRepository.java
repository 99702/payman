package com.payman.repository;

import com.payman.entity.Account;
import com.payman.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query(value = "select * from balance b where b.fk_account=:aId", nativeQuery = true)
    Balance fetchByAccountId(Long aId);
}
