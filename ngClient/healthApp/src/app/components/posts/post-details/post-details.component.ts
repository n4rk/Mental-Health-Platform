import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../../model/posts.model';
import { Comment } from '../../../model/comments.model';
import { PostService } from '../../../services/post.service';
import { CommentService } from '../../../services/comment.service';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  id!:string;
  post!:Post;
  comments!:Comment[];

  constructor(private route: ActivatedRoute,private router: Router, private postService: PostService, private commentService:CommentService) { }

  ngOnInit(): void {
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
