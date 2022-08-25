package com.payman.service.impl;

import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.GetAllCustomer;
import com.payman.dto.response.GetAllEmployerAdmin;
import com.payman.entity.Account;
import com.payman.entity.Balance;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.AccountRepository;
import com.payman.repository.BalanceRepository;
import com.payman.repository.CustomerRepository;
import com.payman.repository.StatsRepository;
import com.payman.service.StatsService;
import com.payman.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AES aes;


    @Override
    public List<GetAllCustomer> getAllCustomer(String type) {
        try{
           return this.setterForGetAllCustomer(statsRepository.fetchAllCustomerWhoHaveAccount("customer"));
        } catch(Exception e){
            System.out.println(e);
           throw new PaymanException("Cant get peoples");
        }
   }

    @Override
    public List<GetAllEmployerAdmin> getAllEmployerAdmin(String type) {
        try{
            if(Objects.equals(type, "admin")){
                return setterForGetAllEmployerAdmin(statsRepository.fetchAllCustomer("god"), "god");
            } else{
                return setterForGetAllEmployerAdmin(statsRepository.fetchAllCustomer("employer"), "employer");
            }
        } catch (Exception e){
           throw new PaymanException("Cant get peoples") ;
        }
    }

    @Override
    public List<GetAllEmployerAdmin> getAllCustomerUnregistered(String type) {
        try{
            return this.setterForGetAllEmployerAdmin(statsRepository.fetchAllCustomerWhoDontHaveAccount("customer"), "customer");
        } catch(Exception e){
            System.out.println(e);
            throw new PaymanException("Cant get peoples");
        }
    }

    private List<GetAllCustomer> setterForGetAllCustomer(List<Customer> customers){
        List<GetAllCustomer>  getAllCustomerDetailsList = new ArrayList<>();
        for(Customer customer: customers){
            GetAllCustomer getAllCustomer = new GetAllCustomer();
            Account account = accountRepository.fetchByCustomerId(customer.getId());
            Balance balance = balanceRepository.fetchByAccountId(account.getId());
            getAllCustomer.setAccount_number(aes.decryptText("AES",account.getAccountNumber()));
            getAllCustomer.setBalance(balance.getBalance());
            getAllCustomer.setGender(customer.getGender());
            getAllCustomer.setFull_name(customer.getFullName());
            getAllCustomer.setMobile(aes.decryptText("AES", customer.getMobile()));
            getAllCustomer.setCitizenship_number(customer.getCitizenshipNo());
            getAllCustomer.setDob(customer.getDob());
            getAllCustomerDetailsList.add(getAllCustomer);
        }
        return getAllCustomerDetailsList;
    }

    private  List<GetAllEmployerAdmin> setterForGetAllEmployerAdmin(List<Customer> customers, String type){
        List<GetAllEmployerAdmin> getAllEmployerAdminList = new ArrayList<>();
        for(Customer customer: customers){
           GetAllEmployerAdmin getAllEmployerAdmin = new GetAllEmployerAdmin() ;
           getAllEmployerAdmin.setGender(customer.getGender());
           getAllEmployerAdmin.setDob(customer.getDob());
           getAllEmployerAdmin.setEnabled(customer.getEnabled());
           getAllEmployerAdmin.setFullName(customer.getFullName());
           getAllEmployerAdmin.setCitizenshipNo(customer.getCitizenshipNo());
           getAllEmployerAdmin.setCreatedAt(customer.getCreatedAt());
           getAllEmployerAdmin.setMobile(aes.decryptText("AES",customer.getMobile()));
           getAllEmployerAdminList.add(getAllEmployerAdmin);
        }
        return getAllEmployerAdminList;
    }
}