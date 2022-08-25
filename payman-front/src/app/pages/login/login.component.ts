import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
    hidePassword: boolean = true;
    error = {
        status: false,
        message: "ding ding ding ding"
    }

    form: FormGroup = new FormGroup({
        mobile: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("^[0-9]*$")]),
        password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    });

    constructor(private login: LoginService, private router: Router) { }
    ngOnInit(): void {
    }

    getErrorMessage() {
        if (this.form.get("phone")?.hasError("required")) {
            return "Please enter your mobile number";
        }

        if (this.form.get("phone")?.hasError("pattern") || this.form.get("phone")?.hasError("minLength") || this.form.get("phone")?.hasError("maxLength")) {
            return "Please enter valid phone number";
        }
        if (this.form.get("password")?.hasError("required")) {
            return "Please enter your password";
        }
        if (this.form.get("password")?.hasError("minLength")) {
            return "Password should be of minimum 8 length";
        }
        return;
    }

    // if form is valid then return false
    checkFormValidation(): boolean {
        return this.form.valid;
    }

    // login submit event function
    submit() {
        console.log("Login button clicked")

        // now request our service to generate token 😘
        this.login.generateToken(this.form.value).subscribe(
            (data: any) => {
                /// success

                //login
                this.login.loginUser(data.token)

                //get current user
                this.login.getCurrentUser().subscribe(
                    (user: any) => {
                        this.login.setUser(user);

                        // check if user is god, employer, customer (redirect to ther pages)
                        if (this.login.getType() == "god") {
                            this.router.navigate(["/admin"])
                        } else if (this.login.getType() == "employer") {
                            this.router.navigate(["/employer"])
                        } else {
                            this.router.navigate(["/dashboard"])
                        }
                    },
                    (error: any) => {
                        console.log("error =- currentuser")
                        console.log(error)
                    }
                )
                console.log("---------------------------------")
                console.log("success")
                console.log(data)
                console.log("---------------------------------")
            },

            (error: any) => {
                this.error.status = true;
                this.error.message = error.error.message ? error.error.message : "Server error";
                console.log(error)
            }
        )
    }
}
