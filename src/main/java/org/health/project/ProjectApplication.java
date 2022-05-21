package org.health.project;

import org.health.project.dtos.CommentDto;
import org.health.project.dtos.NotePadDto;
import org.health.project.dtos.PostDto;
import org.health.project.entites.Comment;
import org.health.project.entites.Post;
import org.health.project.mappers.PostMapper.PostMapper;
import org.health.project.mappers.commentMapper.CommentMapper;
import org.health.project.services.NotePadService.NotePadService;
import org.health.project.services.commentService.CommentService;
import org.health.project.services.postService.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }


    @Bean
    CommandLineRunner start(NotePadService notePadService,PostService postService, CommentService commentService, PostMapper postMapper, CommentMapper commentMapper){
        return args -> {
            for (int i = 0; i < 5; i++) {
                PostDto postDto = new PostDto();
                postDto.setDatePosted(new Date());
                postDto.setContent("test content"+i);
                PostDto savePostDto = postService.savePost(postDto);
                //postService.savePost(postDto);
                Post post = postMapper.fromPostDtoToPost(savePostDto);

                for (int j = 0; j < 3; j++) {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setDate_posted(new Date());
                    commentDto.setContent("test content");
                    commentDto.setPostDto(postDto);
                    commentService.saveComment(commentDto);
                }
                //CommentDto savedCommentDto = commentService.saveComment(commentDto);

                //Comment comment = commentMapper.from_CommentDto_To_Comment(savedCommentDto);

                //comment.setPost(post);

            }


            for (int i = 0; i < 5; i++) {
                NotePadDto notePadDto = new NotePadDto();
                notePadDto.setTitle("note "+i);
                notePadDto.setDateCreated(new Date());
                notePadDto.setContent("note content");
                notePadService.saveNote(notePadDto);
            }
        };
    }
}
