import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../model/posts.model";
import {Comment} from "../model/comments.model";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = "http://localhost:8085/posts/";

  constructor(private http: HttpClient) { }

  public getAllPosts():Observable<Post[]> {
    return this.http.get<Post[]>(`${this.baseUrl}`);
  }

  public getPost(id:string):Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}`+id);
  }

  public getComments(id:string):Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}`+id+"/comments");
  }
  public createPost(post: Post): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, post);
  }

  public updatePost(id: string, post:Post): Observable<Object> {
    return this.http.put(`${this.baseUrl}`+id, post);
  }

  public deletePost(id:string): Observable<Object> {
    return this.http.delete<Post>(`${this.baseUrl}`+id);
  }
}
