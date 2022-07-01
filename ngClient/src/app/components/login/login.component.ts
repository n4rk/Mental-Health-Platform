import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import {SecurityService} from "../../services/security.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm  !: FormGroup;
  submitting =  false;

  constructor(
    private fb:FormBuilder,
    private securityService: SecurityService,
    private router:Router
  ) {

    this.loginForm = this.fb.group({
      username : this.fb.control("",[
        Validators.required, Validators.minLength(3)
      ]),
      password: this.fb.control("", [
        Validators.required, Validators.minLength(4)
      ])
    })

  }

  ngOnInit(): void {
  }

  handleLoginSubmit(){
    this.submitting=true
    this.securityService.loginRequest( this.loginForm.value.username, this.loginForm.value.password)
      .then(bol=>{
        if(bol){
          alert("You have successfully logged in !")
          this.router.navigate(["/"]);
        }
      }).catch(any=>{
      alert('Incorrect Username or Password !')
    }).finally(()=>{
      this.submitting=false
    })
  }
}
