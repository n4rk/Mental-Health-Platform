package org.health.project.mappers.commentMapper;

import org.health.project.dtos.CommentDto;
import org.health.project.entites.Comment;

public interface CommentMapper {
    CommentDto from_Comment_To_CommentDto(Comment comment);
    Comment from_CommentDto_To_Comment(CommentDto commentDto);
}
