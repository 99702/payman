import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
    providedIn: 'root'
})
export class RedirectorGuard implements CanActivate {

    constructor(private login: LoginService, private http: HttpClient, private router: Router) { };
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.login.getUser != null) {
            this.router.navigate([""]);
        }
        return true;
    }
}
