package org.health.project.mappers.PostMapper;

import org.health.project.dtos.PostDto;
import org.health.project.entites.Post;

public interface PostMapper {
    PostDto fromPostToPostDto(Post post);
    Post fromPostDtoToPost(PostDto postDto);
}
