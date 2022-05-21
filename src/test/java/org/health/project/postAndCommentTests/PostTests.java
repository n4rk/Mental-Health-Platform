package org.health.project.postAndCommentTests;

import org.health.project.dtos.PostDto;
import org.health.project.entites.Post;
import org.health.project.mappers.PostMapper.PostMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;


import java.util.Date;
import java.util.UUID;

//@SpringBootTest
public class PostTests {

    ModelMapper modelMapper;

    PostMapperImpl postMapper;

    @BeforeEach
    void setUp() {
        postMapper = new PostMapperImpl();
    }

    @Test
    public void testFrom_PostDto_To_Post_using_modelMapper(){
        //given
        PostDto postDto = new PostDto();
        postDto.setId(UUID.randomUUID().toString());
        postDto.setDatePosted(new Date());
        postDto.setContent("test content");

        //when
        Post post = postMapper.fromPostDtoToPost(postDto);

        //then
        Assert.assertEquals(post.getId(),postDto.getId());
        Assert.assertEquals(post.getDatePosted(),postDto.getDatePosted());
        Assert.assertEquals(post.getContent(),post.getContent());
    }

    @Test
    public void test_fromPost_To_PostDto(){
        PostDto postDto = new PostDto();
        postDto.setId("1");
        postDto.setDatePosted(new Date());
        postDto.setContent("content");

        Post post = postMapper.fromPostDtoToPost(postDto);

        Assert.assertEquals(post.getId(),postDto.getId());
        Assert.assertEquals(post.getContent(),postDto.getContent());
        Assert.assertEquals(post.getDatePosted(),postDto.getDatePosted());

    }
}
