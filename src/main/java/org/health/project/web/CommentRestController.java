package org.health.project.web;

import lombok.AllArgsConstructor;
import org.health.project.dtos.CommentDto;
import org.health.project.dtos.PostDto;
import org.health.project.services.commentService.CommentService;
import org.health.project.services.postService.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class CommentRestController {
    private CommentService commentService;
    private PostService postService;

    @PostMapping(path = "/posts/{id}")
    public CommentDto saveComment(@RequestBody CommentDto commentDto,@PathVariable String id){
        PostDto post = postService.getPost(id);
        commentDto.setPostDto(post);
        return commentService.saveComment(commentDto);
    }


    @PutMapping(path = "/posts/{id}/comments/{id_comment}")
    public CommentDto updateComment(@RequestBody String content,
                                    @PathVariable Long id_comment,
                                    @PathVariable String id){
        CommentDto commentDto = commentService.updateComment(id_comment,id);
        commentDto.setDate_posted(new Date());
        commentDto.setContent(content);
        return commentService.saveComment(commentDto);
    }

    @DeleteMapping(path = "/posts/comments/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
