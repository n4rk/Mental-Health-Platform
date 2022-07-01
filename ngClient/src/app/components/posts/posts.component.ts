import { Component, OnInit } from '@angular/core';
import { PostService } from "../../services/post.service";
import { Router } from '@angular/router';
import { Post } from "../../model/posts.model";
import { Comment } from "../../model/comments.model";
import { User } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import { SecurityService } from 'src/app/services/security.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  posts! : Post[];
  comments!: Comment[];
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;

  constructor(private securityService:SecurityService, private postService:PostService, private router: Router) {
    this.userSub$ = this.securityService.userSubject.subscribe({
      next: user=>{
        this.user = user;
        this.userRole = user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
      },
      error: (err)=>{
        console.error(err)
      }
    })
  };

  ngOnInit(): void {
    this.securityService.getUser()
    this.user = this.securityService.user;
    this.userRole = this.securityService.user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
    this.getPosts();
  }

  private getPosts() {
    this.postService.getAllPosts().subscribe(data => {
        this.posts = data;
      }, error => console.log(error)
    );
  }

  getPost(id: string){
    this.router.navigate(['posts', id]);
  }

  getComments(id:string) {
    this.postService.getComments(id).subscribe(data => {
      this.comments = data;
    }, error => console.log(error)
  );
  }

  createPost() {
    this.router.navigate(['posts','add']);
  }
  
  updatePost(id: string){
    this.router.navigate(['posts','update', id]);
  }
  
  deletePost(id: string) {
    this.postService.deletePost(id)
      .subscribe(
        data => {
          console.log(data);
          this.getPosts();
        },
        error => console.log(error));
  }
}
