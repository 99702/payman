package com.payman.service.impl;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.CurrentUserResponseDTO;
import com.payman.dto.response.LoginHistoryResponse;
import com.payman.dto.response.LoginResponseDTO;
import com.payman.entity.Customer;
import com.payman.entity.Ip;
import com.payman.errors.PaymanException;
import com.payman.repository.CustomerRepository;
import com.payman.repository.IpRepository;
import com.payman.service.AuthService;
import com.payman.utils.AES;
import com.payman.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;
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

    @Override
    public CurrentUserResponseDTO getCurrentLoggedinUser(HttpServletRequest request) {

        // get customer id of currently loggedin user
        String authorizationHeader = request.getHeader("Authorization");
        try{
            String jwt = aes.decryptText("AES", authorizationHeader.substring(7));
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            Long checkCustomerId = Long.valueOf((Integer) claims.get("customerId")); // we get customer id of current loggedin user
            return this.setterForGetCurrentUserResponseDTO(customerRepository.fetchById(checkCustomerId));
        } catch (Exception e){
            System.out.println(e);
            throw new PaymanException("Cannot get the current user");
        }
    }

    @Override
    public List<LoginHistoryResponse> getLoginHistory(HttpServletRequest request) {
        // get customer id of currently loggedin user
        String authorizationHeader = request.getHeader("Authorization");
        try{
            String jwt = aes.decryptText("AES", authorizationHeader.substring(7));
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            Long checkCustomerId = Long.valueOf((Integer) claims.get("customerId")); // we get customer id of current loggedin user
            return this.setterForLoginHistory(customerRepository.getCustomerLoginHistory(checkCustomerId));
        } catch (Exception e){
            System.out.println(e);
            throw new PaymanException("Cannot get the current user");
        }


    }

    private LoginResponseDTO setterForLoginResponseDTO(Customer customer, String message, String token){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setFullName(customer.getFullName());
        loginResponseDTO.setMessage(message);
        loginResponseDTO.setToken(token);
        return loginResponseDTO;
    }

    private CurrentUserResponseDTO setterForGetCurrentUserResponseDTO(Customer customer){
        CurrentUserResponseDTO currentUserResponseDTO = new CurrentUserResponseDTO();
        currentUserResponseDTO.setMobile(aes.decryptText("AES",customer.getMobile()));
        currentUserResponseDTO.setProvince(customer.getProvince());
        currentUserResponseDTO.setFull_name(customer.getFullName());
        currentUserResponseDTO.setCitizenshipNo(customer.getCitizenshipNo());
        currentUserResponseDTO.setCreatedAt(customer.getCreatedAt());
        currentUserResponseDTO.setType(customer.getType());
        currentUserResponseDTO.setDob(customer.getDob());
        return currentUserResponseDTO;
    }

    private List<LoginHistoryResponse> setterForLoginHistory(List<Ip> ips){
        List<LoginHistoryResponse> loginHistoryResponses = new ArrayList<>();
        for(Ip ip: ips){
            LoginHistoryResponse loginHistoryResponse = new LoginHistoryResponse();
            loginHistoryResponse.setAddress(ip.getAddress());
            loginHistoryResponse.setCreatedAt(ip.getCreatedAt());
            loginHistoryResponses.add(loginHistoryResponse);
        }
        return loginHistoryResponses;
    }
}