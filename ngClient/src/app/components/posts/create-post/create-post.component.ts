import { PostService } from '../../../services/post.service';
import { Post } from '../../../model/posts.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityService } from 'src/app/services/security.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  post : Post = new Post();
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;

  constructor(private securityService : SecurityService, private postService: PostService, private router: Router) { 
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

  save() {
    this.postService.createPost(this.post).subscribe(data => {
      console.log(data);
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    console.log(this.post);
    this.save();
  }

  gotoList() {
    this.router.navigate(['/posts']);
  }

}
