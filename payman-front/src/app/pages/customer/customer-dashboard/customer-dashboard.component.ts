import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
    selector: 'app-customer-dashboard',
    templateUrl: './customer-dashboard.component.html',
    styleUrls: ['./customer-dashboard.component.css']
})

export class CustomerDashboardComponent implements OnInit {
    step: string = "";
    constructor(private router: Router, private login: LoginService) {
    }
    ngOnInit(): void {
        console.log(this.router.url)
    }
    logout() {
        this.login.logout();
        this.router.navigate(["/login"]);
    }
}
