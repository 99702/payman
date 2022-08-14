package com.payman.service.impl;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.LoginResponseDTO;
import com.payman.entity.Customer;
import com.payman.entity.Ip;
import com.payman.errors.PaymanException;
import com.payman.repository.CustomerRepository;
import com.payman.repository.IpRepository;
import com.payman.service.AuthService;
import com.payman.utils.AES;
import com.payman.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AES aes;

    @Autowired
    IpRepository ipRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
        String userIp = request.getHeader("IP");
        String mobilePlain = loginRequestDTO.getMobile();
        Customer customer  = customerRepository.fetchByMobileExact(aes.encryptText("AES", mobilePlain));

        try{
            // throw error if ip is null
            if(userIp == null){
                throw new PaymanException("IP NUll");
            }

            // check if that person exists
            if(customer == null){
                throw new PaymanException("Customer doesnt exists");
            }

            // check if password and given password matches
            String decodedPasswordHash = aes.decryptText("AES", customer.getPassword());
            boolean result = passwordEncoder.matches(loginRequestDTO.getPassword(), decodedPasswordHash);

            if(!result){ // if database password and request password in request throw
                throw new PaymanException("Password doesn't matches");
            }

            //generate jwt token of that user
            UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getMobile());
            String jwtToken = aes.encryptText("AES", jwtUtil.generateToken(userDetails));

            // save user login ip address
            Ip ip = new Ip();
            ip.setAddress(userIp);
            ip.setCustomer(customer);
            ipRepository.save(ip);

            return this.setterForLoginResponseDTO(customer, "success", jwtToken);
        } catch (Exception e){
           throw new PaymanException(e.getMessage());
        }
    }

    private LoginResponseDTO setterForLoginResponseDTO(Customer customer, String message, String token){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setFullName(customer.getFullName());
        loginResponseDTO.setMessage(message);
        loginResponseDTO.setToken(token);
        return loginResponseDTO;
    }
}
