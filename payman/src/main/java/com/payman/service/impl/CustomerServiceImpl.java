package com.payman.service.impl;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.request.ResetPasswordRequestDTO;
import com.payman.dto.response.ResetPasswordResponseDto;
import com.payman.dto.request.UpdateCustomerRequest;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.dto.response.UpdateSuccess;
import com.payman.entity.Account;
import com.payman.entity.Balance;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.AccountRepository;
import com.payman.repository.BalanceRepository;
import com.payman.repository.CustomerRepository;
import com.payman.service.CustomerService;
import com.payman.utils.AES;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    AccountRepository accountRepository;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AES aes;

    @Override
    public RegisterACustomerDTOResponse registerACustomer(@RequestBody RegisterACustomerDTO registerACustomerDTO) {
        Customer customer = new Customer();
        customer.setCitizenshipNo(registerACustomerDTO.getCitizenshipNo());
        customer.setDob(registerACustomerDTO.getDob());
        customer.setFullName(registerACustomerDTO.getFullName());
        customer.setMobile(aes.encryptText("AES",registerACustomerDTO.getMobile()));
        customer.setProvince(registerACustomerDTO.getProvince());
        customer.setPassword(aes.encryptText("AES",passwordEncoder.encode(registerACustomerDTO.getPassword())));
        customer.setGender(registerACustomerDTO.getGender());
        customerRepository.save(customer);
        return this.setterForRegisterACustomerDTOResponse("Registered successfully ", "success", customer.getFullName());
    }


    @Override
    public GetACustomerDetails getCustomerFromAccNo(String accountNumber) {
        try{
            // get customer from that accountNumber
            Customer customer = customerRepository.fetchByAccountNumber(aes.encryptText("AES", accountNumber));
            //basically if customer doesn't exists

            if(customer == null){
                throw new PaymanException("Customer doesn't exists ");
            }

            return this.setterForGetACustomerDetails(customer);
        } catch(Exception e){
            throw new PaymanException(e.getMessage());
        }
    }

    @Override
    public UpdateSuccess updateCustomer(UpdateCustomerRequest updateCustomerRequest, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        try {
            String jwt = aes.decryptText("AES", authorizationHeader.substring(7));
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            Long getCustomerId = Long.valueOf((Integer) claims.get("customerId")); // we get customer id of current loggedin user
            Customer loggedinCustomer = customerRepository.fetchById(getCustomerId);

            // check password matches is null , Reject
            if(updateCustomerRequest.getPasswordCheck() == null)
                throw new PaymanException("Enter your password to update");

            /// else
             if(updateCustomerRequest.getPasswordCheck() != null){
                 // check if confirmation password  matches user password
                 String decodedPasswordHash = aes.decryptText("AES", loggedinCustomer.getPassword());
                 boolean result = passwordEncoder.matches(updateCustomerRequest.getPasswordCheck(), decodedPasswordHash);
                 if(!result){ // if database password and confirmation password in request throw
                     throw new PaymanException("Password doesn't matches");
                 }
             }

            // full name
            if (Objects.nonNull(updateCustomerRequest.getFullName()) && !"".equalsIgnoreCase(updateCustomerRequest.getFullName())) {
                loggedinCustomer.setFullName(updateCustomerRequest.getFullName());
            }

            // dob
           if (Objects.nonNull(updateCustomerRequest.getDob())) {
                loggedinCustomer.setDob(updateCustomerRequest.getDob());
            }

           // province
            if (Objects.nonNull(updateCustomerRequest.getProvince())) {
                loggedinCustomer.setProvince(updateCustomerRequest.getProvince());
            }

            // citizenship number
            if (Objects.nonNull(updateCustomerRequest.getCitizenshipNo()) && customerRepository.getCustomerFromCitizenshipNumber(updateCustomerRequest.getCitizenshipNo())== null) {
                loggedinCustomer.setCitizenshipNo(updateCustomerRequest.getCitizenshipNo());
            }


            // now save the loggedin user
            customerRepository.save(loggedinCustomer);
            return this.setterForUpdateSuccess(aes.decryptText("AES",loggedinCustomer.getMobile())+ " is updated successfully.");
        } catch (Exception e){
            System.out.println(e);
            throw new PaymanException(e.getMessage());
        }
    }

    @Override
    public String savePasswordResetToken(String mobile, String token) {
        try{
            Customer customer = customerRepository.fetchByMobileExact(aes.encryptText("AES",mobile));
            customer.setResetPasswordToken(aes.encryptText("AES" ,token));
            customerRepository.save(customer);

            return  "Success";

        } catch (Exception e){
            throw new PaymanException("Something went wrong :(");
        }

    }
    @Override
    public ResetPasswordResponseDto resetPasswordDone(ResetPasswordRequestDTO resetPasswordRequestDTO, String token) {
        try {
           Customer customer = customerRepository.getCustomerByResetPasswordToken(aes.encryptText("AES",token)) ;
           System.out.println(customer);

           if(customer == null){
               throw new PaymanException("Invalid token");
           }

            // check if New password and database password matches, then reject it
//            String decodedPasswordHash = aes.decryptText("AES", customer.getPassword());
//            boolean result = passwordEncoder.matches(resetPasswordRequestDTO.getNewPassword(), decodedPasswordHash);
//
//            if(result){ // if database password and request password
//                throw new PaymanException("Please choose different password");
// }

            // change password and delete that token from user
           customer.setPassword(aes.encryptText("AES",passwordEncoder.encode(resetPasswordRequestDTO.getNewPassword())));
           customer.setResetPasswordToken(null);
           customerRepository.save(customer);

           return this.setterForResetPasswordResponseDto();
        }catch (Exception e){
            throw new PaymanException(e.getMessage());

        }
    }
    private RegisterACustomerDTOResponse setterForRegisterACustomerDTOResponse(String message, String status, String fullName){
        RegisterACustomerDTOResponse registerACustomerDTOResponse = new RegisterACustomerDTOResponse();
        registerACustomerDTOResponse.setMessage(message);
        registerACustomerDTOResponse.setStatus(status);
        registerACustomerDTOResponse.setFullName(fullName);
        return registerACustomerDTOResponse;
    }
    private  UpdateSuccess setterForUpdateSuccess(String message){
        UpdateSuccess updateSuccess = new UpdateSuccess();
        updateSuccess.setMessage(message);
        return updateSuccess;
    }

    private GetACustomerDetails setterForGetACustomerDetails(Customer customer){
        GetACustomerDetails getACustomerDetails = new GetACustomerDetails();
        Account account = accountRepository.fetchByCustomerId(customer.getId());
        Balance balance = balanceRepository.fetchByAccountId(account.getId());
        getACustomerDetails.setBalance(balance.getBalance());
        getACustomerDetails.setGender(customer.getGender() == null ? "UNKNOWN": String.valueOf(customer.getGender()));
        getACustomerDetails.setFull_name(customer.getFullName());
        getACustomerDetails.setMobile(aes.decryptText("AES",customer.getMobile()));
        getACustomerDetails.setCitizenship_number(customer.getCitizenshipNo());
        getACustomerDetails.setDob(customer.getDob());
        return getACustomerDetails;
    }
    private ResetPasswordResponseDto setterForResetPasswordResponseDto(){
        ResetPasswordResponseDto resetPasswordResponseDto = new ResetPasswordResponseDto();
        resetPasswordResponseDto.setMessage("Successful");
        return resetPasswordResponseDto;
    }
}
