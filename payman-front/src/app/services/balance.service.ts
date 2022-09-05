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
            "screen": `${screen.width}x${screen.height}`,
            "clientDateTime": String(new Date()),
            "language": `${window.navigator.languages}`,
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
            "screen": `${screen.width}x${screen.height}`,
            "clientDateTime": String(new Date()),
            "language": `${window.navigator.languages}`,
        })
        return this.http.get(`${baseUrl}/balance/check`, { headers: headers });
    }
}
