package com.payman.controller;

import com.payman.dto.response.GetAllCustomer;
import com.payman.dto.response.GetAllEmployerAdmin;
import com.payman.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    StatsService statsService;

    // get all customers
    @GetMapping("/all/customer")
    private List<GetAllCustomer> getAllCustomer(@RequestParam("type") String type){
        return statsService.getAllCustomer(type);
    }

    // get all customer unregistered

    @GetMapping("/all/customer/unregistered")
    private  List<GetAllEmployerAdmin> getAllCustomerUnregistered(@RequestParam("type") String type){
        return statsService.getAllCustomerUnregistered(type);
    }

    @GetMapping("/all/ea")
    private  List<GetAllEmployerAdmin> getAllEmployerAdmins(@RequestParam("type") String type){
        return statsService.getAllEmployerAdmin(type);
    }
}
