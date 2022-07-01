import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NotePad } from '../model/notes.model';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private baseUrl = "http://localhost:8085/notes/";

  constructor(private http: HttpClient) { }

  public getAllNotes():Observable<NotePad[]> {
    return this.http.get<NotePad[]>(`${this.baseUrl}`);
  }

  public getNote(id:number):Observable<NotePad> {
    return this.http.get<NotePad>(`${this.baseUrl}`+id);
  }

  public createNote(note: NotePad): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, note);
  }

  public modifyNote(id: number, note:NotePad): Observable<Object> {
    return this.http.put(`${this.baseUrl}`+id, note);
  }

  public deleteNote(id:number): Observable<Object> {
    return this.http.delete<NotePad>(`${this.baseUrl}`+id);
  }
}
