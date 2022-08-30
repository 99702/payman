import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BalanceService } from 'src/app/services/balance.service';
import Swal from 'sweetalert2';

@Component({
    selector: 'app-dialog',
    templateUrl: './dialog.component.html',
    styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
    constructor(public dialogRef: MatDialogRef<DialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private balance: BalanceService, private router: Router) { }
    ngOnInit(): void {
    }

    close(): void {
        this.dialogRef.close();
    }

    yes(): void {
        // transfer balance to this.data.to 
        this.balance.transferBalance(this.data.to, this.data).subscribe(
            (data: any) => {
                Swal.fire(
                    `Successfully transferred Rs.${data.balance} to <h1>${this.data.to}</h1>`,
                    'You clicked the button!',
                    'success'
                )
                this.router.navigate(["/dashboard/transaction/history"])
            },

            (error: any) => {
                Swal.fire(
                    error.error.message ? error.error.message : "Cant transfer to your account",
                    '',
                    'error'
                )
                this.router.navigate(["/dashboard"])
            }
        )
        console.log(this.data)
    }
}
