import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../../model/posts.model';
import { Comment } from '../../../model/comments.model';
import { PostService } from '../../../services/post.service';
import { CommentService } from '../../../services/comment.service';
import { SecurityService } from 'src/app/services/security.service';
import { User } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  id!:string;
  post!:Post;
  comments!:Comment[];
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;

  constructor(private securityService : SecurityService, private route: ActivatedRoute,
    private router: Router, private postService: PostService, private commentService:CommentService) {
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
    this.post = new Post();

    this.id = this.route.snapshot.params['id'];

    this.postService.getPost(this.id).subscribe(data => {
        console.log(data)
        this.post = data;
      }, error => console.log(error));

    this.getAllComments();

  }

  getAllComments() {
    this.postService.getComments(this.id).subscribe(data => {
      console.log(data)
      this.comments = data;
    }, error => console.log(error));
  }

  createComment() {
    this.router.navigate(['/posts',this.id,'add']);
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id).subscribe(
        data => {
          console.log(data);
          this.getAllComments();
        },
        error => console.log(error));
  }

  gotoList(){
    this.router.navigate(['posts']);
  }

}
