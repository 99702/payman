import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransactionHistory } from '../dto/TransactionHistory';
import baseUrl from './helper';

@Injectable({
    providedIn: 'root'
})
export class TransactionService {
    constructor(private http: HttpClient) { }

    // get transaction history of current user
    public getTransactionHistory() {
        return this.http.get<TransactionHistory[]>(`${baseUrl}/transaction/history`);
    }
}
