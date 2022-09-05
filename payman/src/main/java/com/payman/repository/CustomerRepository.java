package com.payman.repository;

import com.payman.entity.Customer;
import com.payman.entity.Ip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query(value = "SELECT c FROM Customer c where c.mobile = :mobile")
    Customer fetchByMobileExact(String mobile);

    @Query(value = "SELECT * FROM customer c where c.id = :cId", nativeQuery = true)
    Customer fetchById(Long cId);

    @Query(value="select c.* from customer c inner join account a on a.fk_customer=c.id where a.account_number=:accountNumber", nativeQuery = true)
    Customer fetchByAccountNumber(String accountNumber);


    @Query(value="select c.* from customer c inner join account a on a.fk_customer=c.id where a.id=:accountId", nativeQuery = true)
    Customer fetchByAccountId(Long accountId);

    @Query(value="select c.* from customer c where c.citizenship_no=:citizenshipNumber", nativeQuery = true)
    Customer getCustomerFromCitizenshipNumber(String citizenshipNumber);

    @Query(value="select p from Ip p where p.customer.id=:cId ORDER BY p.createdAt DESC ")
    Page<Ip> getCustomerLoginHistory(Long cId, Pageable pageable);

    @Query(value="select c.* from customer c where c.reset_password_token=:token", nativeQuery=true)
    Customer getCustomerByResetPasswordToken(String token);
}