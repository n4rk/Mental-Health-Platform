package org.health.project.mappers.PostMapper;

import org.health.project.dtos.PostDto;
import org.health.project.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper{
    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public PostDto fromPostToPostDto(Post post) {
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public Post fromPostDtoToPost(PostDto postDto) {
        Post post = modelMapper.map(postDto,Post.class);
        return post;
    }
}
