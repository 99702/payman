import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerDetailDto } from '../dto/CustomerDetailDto';
import { CustomerListDto } from '../dto/CustomerListDto';
import { CustomerUpdateRequestDto } from '../dto/CustomerUpdateRequestDto';
import baseUrl from './helper';

@Injectable({
    providedIn: 'root'
})
export class CustomerService {
    constructor(private http: HttpClient) { }

    // get all customer
    public getAllCustomer() {
        return this.http.get<CustomerListDto[]>(`${baseUrl}/stats/all/customer?type=customer`)
    }

    // get all customer unregistered
    public getAllCustomerUnregistered() {
        return this.http.get(`${baseUrl}/stats/all/customer/unregistered?type=customer`)
    }
    // get all employer
    public getAllEmployer() {
        return this.http.get(`${baseUrl}/stats/all/ea?type=employer`)
    }

    // get all admin
    public getAllAdmin() {
        return this.http.get(`${baseUrl}/stats/all/ea?type=admin`)
    }

    //register a customer
    public registerCustomer(customerRegisterData: any) {
        return this.http.post(`${baseUrl}/customer/register`, customerRegisterData);
    }

    // get customer by account number
    public getCustomerByAccountNumber(accNo: any) {
        return this.http.get<CustomerDetailDto>(`http://localhost:8082/customer/get/${accNo}`);
    }

    // update customer
    public updateCustomer(customerUpdateRequest: CustomerUpdateRequestDto) {
        return this.http.post(`http://localhost:8082/customer/update`, customerUpdateRequest);
    }
}
