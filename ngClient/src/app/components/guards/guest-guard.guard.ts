import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {SecurityService} from "../../services/security.service";

@Injectable({
  providedIn: 'root'
})
export class GuestGuardGuard implements CanActivate {
  constructor( private securityService:SecurityService, private router:Router){
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.securityService.user!=undefined&&this.securityService.access_token.length>0) {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }

}
