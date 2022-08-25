import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-customer-add',
    templateUrl: './customer-add.component.html',
    styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent implements OnInit {
    hidePassword: boolean = true;
    error = {
        status: false,
        message: "Ding ding ding ding"
    }

    form: FormGroup = new FormGroup({
        mobile: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("^[0-9]*$")]),
        password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    });

    checkFormValidation(): boolean {
        return this.form.valid;
    }
    getErrorMessage() {
        return;
    }

    constructor() { }

    ngOnInit(): void {
    }

    addCustomer() {

    }

}
