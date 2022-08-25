import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
    providedIn: 'root'
})

export class LoginService {
    constructor(private http: HttpClient) { }

    // generate token
    public generateToken(loginData: any) {

        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            "IP": this.getIp()
        });

        //get user ip first
        return this.http.post(`${baseUrl}/auth/login`, loginData, { headers: headers })
    }

    // login user set token to sessionStorage 
    public loginUser(token: any) {
        sessionStorage.setItem('token', token);
        return true;
    }

    // check if user is loggedin or not
    public isLoggedIn() {
        let tokenStr = sessionStorage.getItem('token');
        if (tokenStr == undefined || tokenStr == "" || tokenStr == null) {
            return false;
        } else {
            return true;
        }
    }

    //logout user
    public logout() {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("user");
        return true;
    }

    // get token
    public getToken() {
        return sessionStorage.getItem("token");
    }

    // also save userDetails to sessionStorage
    public setUser(user: any) {
        sessionStorage.setItem('user', JSON.stringify(user))
    }

    // get a user from sessionStorage 
    public getUser() {
        let userStr = sessionStorage.getItem("user");
        if (userStr != null) {
            return JSON.parse(userStr);
        } else {
            this.logout()
            return null;
        }
    }

    // get user type 
    public getType() {
        let user = this.getUser();
        return user.type;
    }

    // get current loggedin user
    public getCurrentUser() {
        return this.http.get(`${baseUrl}/auth/current`);
    }

    // get ip address of current user
    public getIp() {
        let ip = "";
        this.http.get("http://api.ipify.org/?format=json").subscribe((res: any) => {
            ip = res.ip;
        });
        return ip;
    }
}
