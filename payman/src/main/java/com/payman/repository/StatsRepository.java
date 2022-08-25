package com.payman.repository;

import com.payman.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatsRepository extends JpaRepository<Customer, Long> {
    @Query(value="SELECT * from customer c where c.type=:type ", nativeQuery = true)
    List<Customer> fetchAllCustomer(String type);

    @Query(value="select * from customer c join account a on c.id=a.fk_customer where c.type=:type ", nativeQuery = true)
    List<Customer> fetchAllCustomerWhoHaveAccount(String type);

    @Query(value="select * from customer c where not exists (select * from account a where a.fk_customer = c.id ) and c.type=:type", nativeQuery = true)
    List<Customer> fetchAllCustomerWhoDontHaveAccount(String type);

}
