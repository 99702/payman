import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
    selector: 'app-login-history',
    templateUrl: './login-history.component.html',
    styleUrls: ['./login-history.component.css']
})
export class LoginHistoryComponent implements OnInit {
    histories = null;
    displayedColumns = ["IP", "Login_date"]
    constructor(private login: LoginService) { }
    ngOnInit(): void {
        this.login.getLoginHistory().subscribe(
            (data: any) => {
                this.histories = data;
            },
            (error: any) => {
                console.log(error);
            }
        )

    }

}
