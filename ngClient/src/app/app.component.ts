import { Component } from '@angular/core';
import {User} from "./model/user.model";
import {Subscription} from "rxjs";
import {SecurityService} from "./services/security.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'N7-MentalHealth';
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;

  constructor( private securityService:SecurityService, private router:Router) {
    this.userSub$ = this.securityService.userSubject.subscribe({
      next: user=>{
        this.user = user;
        this.userRole = user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
      },
      error: (err)=>{
        console.error(err)
      }
    })
  }
  ngOnInit(): void {
    this.securityService.getUser()

    this.user = this.securityService.user;
    this.userRole = this.securityService.user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
  }

  logout(){
    this.securityService.logout();
    this.router.navigate(['/']);
  }

  ngOnDestroy(): void {
    this.userSub$?.unsubscribe();
  }
}
