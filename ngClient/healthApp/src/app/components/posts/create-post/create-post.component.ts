import { PostService } from '../../../services/post.service';
import { Post } from '../../../model/posts.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  post : Post = new Post();

  constructor(private postService: PostService, private router: Router) { }

  ngOnInit(): void {
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
