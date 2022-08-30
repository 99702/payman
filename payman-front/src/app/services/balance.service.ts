import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
    providedIn: 'root'
})
export class BalanceService {
    constructor(private http: HttpClient) { }

    //transfer balance to given mobile number
    public transferBalance(mobNo: number, data: any) {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            "ip": "192.168.1.1",
            "latitude": "1.000",
            "longitude": "0.9555",
            "screen": "203x104",
            "clientDateTime": "2022-03-11",
            "language": "Nepali",
        })
        return this.http.post(`${baseUrl}/balance/deposit?mobileNumber=${mobNo}`, data, { headers: headers });
    }

    // check balance of loggedin user
    public getBalance() {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            "ip": "192.168.1.1",
            "latitude": "1.000",
            "longitude": "0.9555",
            "screen": "203x104",
            "clientDateTime": "2022-03-11",
            "language": "Nepali",
        })
        return this.http.get(`${baseUrl}/balance/check`, { headers: headers });
    }
}
