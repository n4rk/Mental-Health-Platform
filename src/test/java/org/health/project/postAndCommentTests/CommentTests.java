package org.health.project.postAndCommentTests;

import org.health.project.dtos.CommentDto;
import org.health.project.dtos.PostDto;
import org.health.project.entites.Comment;
import org.health.project.entites.Post;
import org.health.project.mappers.PostMapper.PostMapperImpl;
import org.health.project.mappers.commentMapper.CommentMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CommentTests {

    CommentMapperImpl commentMapper;
    PostTests postTests;

    @BeforeEach
    void setUp() {
        commentMapper = new CommentMapperImpl();
        postTests = new PostTests();
    }

    @Test
    public void test_from_comment_to_CommentDto(){
        Post post = new Post();
        post.setId("1");
        post.setDatePosted(new Date());
        post.setContent("test content");

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("test content");
        comment.setDate_posted(new Date());
        comment.setPost(post);

        CommentDto commentDto = commentMapper.from_Comment_To_CommentDto(comment);


        Assert.assertEquals(commentDto.getId(),comment.getId());
        Assert.assertEquals(commentDto.getContent(),comment.getContent());
        Assert.assertEquals(commentDto.getDate_posted(),comment.getDate_posted());

        Assert.assertEquals(commentDto.getPostDto().getId(),comment.getPost().getId());
        Assert.assertEquals(commentDto.getPostDto().getContent(),comment.getPost().getContent());
        Assert.assertEquals(commentDto.getPostDto().getDatePosted(),comment.getPost().getDatePosted());


    }


    public void test_from_commentDto_to_Comment(){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setContent("test content");
        commentDto.setDate_posted(new Date());

        Comment comment = commentMapper.from_CommentDto_To_Comment(commentDto);

        Assert.assertEquals(comment.getId(),commentDto.getId());
        Assert.assertEquals(comment.getContent(),commentDto.getContent());
        Assert.assertEquals(comment.getPost().getContent(),commentDto.getPostDto().getContent());
        Assert.assertEquals(comment.getPost().getContent(),commentDto.getPostDto().getContent());

    }
}
