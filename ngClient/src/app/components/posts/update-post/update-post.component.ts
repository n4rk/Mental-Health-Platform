import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../../model/posts.model';
import { PostService } from '../../../services/post.service';

@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.css']
})
export class UpdatePostComponent implements OnInit {
  id!: string;
  post : Post = new Post();

  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
    this.postService.getPost(this.id).subscribe(data => {
        this.post = data;
        console.log(this.post);
      }, error => console.log(error));
  }

  onSubmit() {
    this.postService.updatePost(this.id, this.post).subscribe(data => {
      this.gotoList();
    }, error => console.log(error));  
  }

  gotoList() {
    this.router.navigate(['/posts']);
  }
}
