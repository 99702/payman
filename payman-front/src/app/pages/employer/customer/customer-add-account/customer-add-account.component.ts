import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';

@Component({
    selector: 'app-customer-add-account',
    templateUrl: './customer-add-account.component.html',
    styleUrls: ['./customer-add-account.component.css']
})
export class CustomerAddAccountComponent implements OnInit {
    constructor(private account: AccountService, private activatedRoute: ActivatedRoute) { };

    form: FormGroup = new FormGroup({
        mobile: new FormControl(this.activatedRoute.snapshot.paramMap.get("mobNo")),
    })

    ngOnInit(): void {
    }
    addAccount() {
        this.account.createAccount(this.form.value).subscribe(
            (data: any) => {
                /// success
                console.log(data);
            },
            (error: any) => {
                /// error
            }
        )
    }
}
