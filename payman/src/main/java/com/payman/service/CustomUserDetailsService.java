package com.payman.service;

import com.payman.dto.CustomUserDetails;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    //username as mobile
    public UserDetails loadUserByUsername(String username) {
        try {
            Customer customer = customerRepository.fetchByMobileExact(username);
            if (customer == null) {
                throw new PaymanException("User doesn't exist");
            }
            return new CustomUserDetails(customer);
        } catch (Exception e) {
            throw new PaymanException(e.getMessage());
        }
    }
}
