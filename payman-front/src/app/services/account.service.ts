import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
    providedIn: 'root'
})
export class AccountService {

    constructor(private http: HttpClient) { }

    // create an account for customer
    public createAccount(userData: any) {
        return this.http.post(`${baseUrl}/account/register`, userData)

    }

}
