import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {
  private baseUrl = "http://localhost:8085/talk";
  
  constructor(private http: HttpClient) { }

  public getResponse(message:string): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, {"user_input":JSON.stringify(message)});
  }
}
