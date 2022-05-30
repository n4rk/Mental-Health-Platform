import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from 'src/app/model/comments.model';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent implements OnInit {
  comment !: Comment;
  id!: string;

  constructor(private commentService: CommentService, private router: Router, private route: ActivatedRoute) { }
  

  ngOnInit(): void {
    this.comment = new Comment();
    this.id= this.route.snapshot.params['id'];
  }

  save() {
    this.commentService.saveComment(this.id, this.comment).subscribe(data => {
      console.log(data);
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    console.log(this.comment);
    this.save();
  }

  gotoList() {
    this.router.navigate(['/posts',this.id]);
  }

}
