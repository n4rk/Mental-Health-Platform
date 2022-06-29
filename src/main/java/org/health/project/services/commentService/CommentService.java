package org.health.project.services.commentService;

import org.health.project.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto);
    CommentDto updateComment(Long idComment,String id);
    void deleteComment(Long id);
    List<CommentDto> getCommentsFromPost(String id);
}
