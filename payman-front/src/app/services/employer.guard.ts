import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';


@Injectable({
    providedIn: 'root'
})
export class EmployerGuard implements CanActivate {
    constructor(private login: LoginService, private router: Router) { }
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

        // if user is admin return true else return false
        if (this.login.isLoggedIn() && this.login.getType() == "employer") {
            return true;
        }
        return false;
    }
}
