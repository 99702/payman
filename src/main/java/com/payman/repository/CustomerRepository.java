package com.payman.repository;

import com.payman.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c where c.mobile = :mobile")
    Customer fetchByMobileExact(String mobile);

    @Query(value = "SELECT * FROM customer c where c.id = :cId", nativeQuery = true)
    Customer fetchById(Long cId);

    @Query(value="select c.* from customer c inner join account a on a.fk_customer=c.id where a.account_number=:accountNumber", nativeQuery = true)
    Customer fetchByAccountNumber(String accountNumber);
}