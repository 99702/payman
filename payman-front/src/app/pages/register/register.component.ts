import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    hidePassword: boolean = true;
    error = {
        status: false,
        message: "ding ding ding ding"
    }

    form: FormGroup = new FormGroup({
        fullName: new FormControl(''),
        password: new FormControl(''),
        citizenshipNo: new FormControl(''),
        mobile: new FormControl(''),
        dob: new FormControl(null),
        province: new FormControl(''),
        gender: new FormControl(''),
    })

    constructor(private customer: CustomerService) {
    }

    ngOnInit(): void {
    }

    getErrorMessage() {
        return;
    }

    checkormValidation(): boolean {
        return this.form.valid;
    }

    registerCustomer() {
        console.log("Register Customer clicked")
        this.customer.registerCustomer(this.form.value).subscribe(
            (response: any) => {
                console.log(response)
            },

            (error: any) => {
                console.log(error)
            }
        )
    }
}
