package org.health.project.services.postService;

import lombok.AllArgsConstructor;
import org.health.project.dtos.PostDto;
import org.health.project.entites.Post;
import org.health.project.mappers.PostMapper.PostMapper;
import org.health.project.repositories.CommentRepository;
import org.health.project.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private PostMapper postMapper;

    @Override
    public PostDto savePost(PostDto postDto) {
        postDto.setId(UUID.randomUUID().toString());
        postDto.setDatePosted(new Date());
        Post post = postMapper.fromPostDtoToPost(postDto);
        Post savedPost =postRepository.save(post);
        return postMapper.fromPostToPostDto(savedPost);
    }

    @Override
    public PostDto editPost(PostDto postDto) {
        Post post = postMapper.fromPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postMapper.fromPostToPostDto(savedPost);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = postList.stream().map(post -> postMapper.fromPostToPostDto(post)).collect(Collectors.toList());
        return postDtoList;
    }


    @Override
    public PostDto getPost(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("post not found"));
        return postMapper.fromPostToPostDto(post);
    }


    @Override
    public PostDto updatePost(PostDto postDto) {
        Post post = postMapper.fromPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postMapper.fromPostToPostDto(savedPost);
    }
}
