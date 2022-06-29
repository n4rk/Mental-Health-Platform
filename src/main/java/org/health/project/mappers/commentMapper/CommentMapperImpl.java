package org.health.project.mappers.commentMapper;

import org.health.project.dtos.CommentDto;
import org.health.project.entities.Comment;
import org.health.project.mappers.PostMapper.PostMapper;
import org.health.project.mappers.PostMapper.PostMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class CommentMapperImpl implements CommentMapper {
    private ModelMapper modelMapper = new ModelMapper();
    private PostMapper postMapper = new PostMapperImpl();

    @Override
    public CommentDto from_Comment_To_CommentDto(Comment comment) {


        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);

        commentDto.setPostDto(postMapper.fromPostToPostDto(comment.getPost()));

        return commentDto;
    }

    @Override
    public Comment from_CommentDto_To_Comment(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(postMapper.fromPostDtoToPost(commentDto.getPostDto()));
        return comment;
    }
}
