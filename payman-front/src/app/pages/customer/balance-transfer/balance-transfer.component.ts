import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { BalanceService } from 'src/app/services/balance.service';
import { LoginService } from 'src/app/services/login.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
    selector: 'app-balance-transfer',
    templateUrl: './balance-transfer.component.html',
    styleUrls: ['./balance-transfer.component.css']
})

export class BalanceTransferComponent implements OnInit {
    currentBalance: number = 0;
    constructor(public dialog: MatDialog, private login: LoginService, private balance: BalanceService) { }
    form: FormGroup = new FormGroup({
        from: new FormControl(this.login.getUser().mobile, [Validators.required,]),
        to: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("^[0-9]*$")]),
        message: new FormControl('', [Validators.required,]),
        balance: new FormControl("", [Validators.required,]),
    })
    ngOnInit(): void {
        this.balance.getBalance().subscribe(
            (data: any) => {
                this.currentBalance = data.balance
            },
            (error: any) => {
                console.log(error)
            }
        )
    }
    getErrorMessage() {

        if (this.form.get("from")?.hasError("required")) {
            return "Please enter your mobile number";
        }

        if (this.form.get("to")?.hasError("required") || this.form.get("to")?.hasError("pattern") || this.form.get("to")?.hasError("minLength") || this.form.get("to")?.hasError("maxLength")) {
            return "Please enter valid phone number";
        }

        // balance check 
        if (this.form.get("balance")?.hasError("required")) {
            return "Please enter balance to send";
        }

        if (this.form.get("balance")) {
            return "Please enter valid balance";
        }

        if (this.form.get("message")?.hasError("required")) {
            return "Please enter message to ";
        }
        return;
    }

    // if form is valid then return false
    checkFormValidation(): boolean {
        return this.form.valid;
    }

    openDialog() {
        this.dialog.open(DialogComponent, {
            width: "350px",
            data: {
                "from": this.form.get("from")?.value,
                "to": this.form.get("to")?.value,
                "message": this.form.get("message")?.value,
                "balance": this.form.get("balance")?.value
            }
        })
    }
}
