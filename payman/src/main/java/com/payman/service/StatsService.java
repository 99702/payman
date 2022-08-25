package com.payman.service;

import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.GetAllCustomer;
import com.payman.dto.response.GetAllEmployerAdmin;

import java.util.List;

public interface StatsService {
    List<GetAllCustomer> getAllCustomer(String type);
    List<GetAllEmployerAdmin> getAllEmployerAdmin(String type);

    List<GetAllEmployerAdmin> getAllCustomerUnregistered(String type);
}