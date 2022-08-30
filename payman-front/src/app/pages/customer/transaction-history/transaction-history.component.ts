import { Component, OnInit } from '@angular/core';
import { TransactionHistory } from 'src/app/dto/TransactionHistory';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
    selector: 'app-transaction-history',
    templateUrl: './transaction-history.component.html',
    styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
    transactionHistories: TransactionHistory[] = []
    displayedColumns: string[] = ["amount", "to", "Date", "message"]
    constructor(private transaction: TransactionService) {
        this.transaction.getTransactionHistory().subscribe(
            (data: TransactionHistory[]) => {
                this.transactionHistories = data
                console.log(this.transactionHistories)
            },
            (error: any) => {

            }
        )
    }
    ngOnInit(): void {
        this.transaction.getTransactionHistory().subscribe(
            (data: TransactionHistory[]) => {
                this.transactionHistories = data
                console.log(this.transactionHistories)
            },
            (error: any) => {

            }

        )

    }
}
