import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {User} from "../model/user.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private baseUrl = "http://localhost:8085";

  userSubject: Subject<User | undefined> = new Subject();
  user ?: User;
  refresh_token = "";
  access_token = "";


  constructor(private http: HttpClient) {
    this.access_token = localStorage.getItem("access_token") ? localStorage.getItem("access_token") + "" : "";
    console.log("  ----------------  "+this.access_token)
    this.refresh_token = localStorage.getItem("refresh_token") ? localStorage.getItem("refresh_token") + "" : "";
  }


  public loginRequest(username: string, password: string) {
    return new Promise<boolean>((resolve, reject) => {
      this.http.post<any>(this.baseUrl + "/login", {
        username,
        password
      }).subscribe({
        next: res => {
          this.access_token = res.access_token
          this.refresh_token = res.refresh_token
          localStorage.setItem("access_token", this.access_token)
          localStorage.setItem("refresh_token", this.refresh_token)
          this.getUser()
          console.log(this.access_token)
          console.log(this.user?.username)
          resolve(true)
        },
        error: err => {
          this.access_token = this.refresh_token = ""
          reject(false)
        }
      })
    })
  }


  public getUser() {
    if (this.access_token.length == 0) return;
    const authorizationHeader = "Bearer " + this.access_token
    this.http.get<User>(this.baseUrl + "/profile", {
      headers: {
        "Authorization": authorizationHeader
      }
    }).subscribe({
      next: res => {
        this.user = res;
        console.log(" result : "+res)
        this.userSubject.next(res)
      },
      error: err => {
        console.error(err.message);
        this.refreshToken()
      }
    })
  }

  public refreshToken() {
    const authorizationHeader = "Bearer " + this.refresh_token
    return this.http.get<any>(this.baseUrl + "/refresh-token", {
      headers: {
        "Authorization": authorizationHeader
      }
    })
  }

  public logout() {
    this.access_token = this.refresh_token = ""
    localStorage.removeItem("access_token")
    localStorage.removeItem("refresh_token")
    this.user = undefined;
    this.userSubject.next(undefined)
  }
}
