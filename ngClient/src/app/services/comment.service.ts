import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../model/comments.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private baseUrl = "http://localhost:8085/posts/";

  constructor(private http: HttpClient) { }

  public getCommentsFromPost(id:string):Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}`);
  }

  public saveComment(id:string, comment: Comment): Observable<Object> {
    return this.http.post(`${this.baseUrl}`+id, comment);
  }

  public deleteComment(id:number): Observable<Object> {
    return this.http.delete<Comment>(`${this.baseUrl}`+"comments/"+id);
  }
}
