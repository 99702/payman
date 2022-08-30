import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';

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
        fullName: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required]),
        citizenshipNo: new FormControl('', [Validators.required]),
        mobile: new FormControl('', [Validators.required]),
        dob: new FormControl(null, [Validators.required]),
        province: new FormControl('', [Validators.required]),
        gender: new FormControl('', [Validators.required]),
    })

    constructor(private customer: CustomerService, private router: Router) {
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
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: response.message,
                    showConfirmButton: false,
                    timer: 1500
                })
                this.form.reset()
                this.router.navigate(["/login"])
            },

            (error: any) => {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: error.error.message ? error.error.message : "Cant register your account, please contact administrators",
                    showConfirmButton: false,
                    timer: 1500
                })
                this.form.reset();
            }
        )
    }
}
