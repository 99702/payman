import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import Swal from 'sweetalert2';

@Component({
    selector: 'app-customer-add-account',
    templateUrl: './customer-add-account.component.html',
    styleUrls: ['./customer-add-account.component.css']
})
export class CustomerAddAccountComponent implements OnInit {
    constructor(private account: AccountService, private activatedRoute: ActivatedRoute, private router: Router) { };

    form: FormGroup = new FormGroup({
        mobile: new FormControl(this.activatedRoute.snapshot.paramMap.get("mobNo")),
    })

    ngOnInit(): void {
    }
    addAccount() {
        this.account.createAccount(this.form.value).subscribe(
            (data: any) => {
                /// success
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: `${this.form.get("mobile")?.value} is registered successfully`,
                    showConfirmButton: false,
                    timer: 1500
                })
                this.router.navigate(["/employer/customer/registered"])
            },

            (error: any) => {
                // something went wrong
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: error.error.message ? error.error.message : "Cant register your account, please contact administrators",
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        )
    }
}
