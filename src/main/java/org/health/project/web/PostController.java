package org.health.project.web;

import lombok.AllArgsConstructor;
import org.health.project.dtos.CommentDto;
import org.health.project.dtos.PostDto;
import org.health.project.repositories.PostRepository;
import org.health.project.services.commentService.CommentService;
import org.health.project.services.postService.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class PostController {
    private CommentService commentService;
    private PostService postService;

    //this request to display the posts without the comments
   @GetMapping(path = "/posts")
   public List<PostDto> getAllPosts(){ return postService.getPosts(); }


    @GetMapping(path = "/posts/{id}")
    public PostDto getPost(@PathVariable String id){
        return postService.getPost(id);
    }

    //this request to display the comments of a post
    @GetMapping(path = "/posts/{id}/comments")
    public List<CommentDto> getComments(@PathVariable String id){
        return commentService.getCommentsFromPost(id);
    }


    @PostMapping(path = "/posts")
    public PostDto savePost(@RequestBody PostDto postDto){
        return postService.savePost(postDto);
    }


    @PutMapping(path = "/posts/{id}")
    public PostDto updatePost(@RequestBody PostDto postDto , String id){
        postDto.setId(id);
        postDto.setDatePosted(new Date());
        return postService.updatePost(postDto);
    }

    @DeleteMapping(path = "/posts/{id}")
    public void deletePost(@PathVariable String id){
        postService.deletePost(id);
    }
}
