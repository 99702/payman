import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
    selector: 'app-customer-profile',
    templateUrl: './customer-profile.component.html',
    styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

    constructor(private login: LoginService, private customer: CustomerService, private router: Router) { }

    ngOnInit(): void {
    }

    error = {
        status: false,
        message: "ding ding ding ding"
    }
    hidePassword: boolean = true;
    form: FormGroup = new FormGroup({
        fullName: new FormControl(this.login.getUser().full_name, [Validators.required]),
        citizenshipNo: new FormControl(this.login.getUser().citizenshipNo, [Validators.required]),
        dob: new FormControl(this.login.getUser().dob, [Validators.required]),
        province: new FormControl(this.login.getUser().province, [Validators.required]),
        passwordCheck: new FormControl("", [Validators.required]),
    })

    updateCustomer() {
        this.customer.updateCustomer(this.form.value).subscribe(
            (data: any) => {
                Swal.fire(
                    'Successfully updated ',
                    '',
                    'success'
                );
                this.login.logout();
                this.router.navigate(['/login']);
            },
            (error: any) => {
                console.log(error)
                Swal.fire(
                    error.error.message ? error.error.message : "Customer can't be updated, contact adminstrators",
                    '',
                    'error'
                )
                this.router.navigate(["/dashboard/profile"])
            }
        )
    }

    checkormValidation(): boolean {
        return this.form.valid;
    }

    getErrorMessage() {
        return;
    }
}
