import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {SecurityService} from "../../services/security.service";

@Injectable({
  providedIn: 'root'
})
export class AdminGuardGuard implements CanActivate {

  constructor( private securityService:SecurityService, private router:Router){
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let yesRno = this.securityService.user?.roles.find( e=>e.roleName=="ADMIN")!=undefined;

    if (!yesRno) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }

}
