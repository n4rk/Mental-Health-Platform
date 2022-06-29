package org.health.project.services.postService;

import org.health.project.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);
    PostDto editPost(PostDto postDto);
    void deletePost(String id);
    PostDto updatePost(PostDto postDto);
    List<PostDto> getPosts();
    PostDto getPost(String id);
}
