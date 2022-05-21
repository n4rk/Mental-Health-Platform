package org.health.project.services.commentService;

import lombok.AllArgsConstructor;
import org.health.project.dtos.CommentDto;
import org.health.project.dtos.PostDto;
import org.health.project.entites.Comment;
import org.health.project.entites.Post;
import org.health.project.mappers.commentMapper.CommentMapper;
import org.health.project.mappers.commentMapper.CommentMapperImpl;
import org.health.project.repositories.CommentRepository;
import org.health.project.repositories.PostRepository;
import org.health.project.services.postService.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private PostRepository postRepository;
    private PostService postService;

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        Comment comment = commentMapper.from_CommentDto_To_Comment(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.from_Comment_To_CommentDto(savedComment);
    }

    @Override
    public CommentDto updateComment(Long idComment,String id) {
        Comment comment = commentRepository.findById(idComment).get();
        CommentDto commentDto = commentMapper.from_Comment_To_CommentDto(comment);
        PostDto postDto = postService.getPost(id);
        commentDto.setPostDto(postDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.from_Comment_To_CommentDto(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }



    @Override
    public List<CommentDto> getCommentsFromPost(String id) {
        Post post = postRepository.findById(id).get();
        List<Comment> commentList = post.getCommentList();
        List<CommentDto> commentDtoList = commentList.stream().map(comment -> commentMapper.from_Comment_To_CommentDto(comment)).collect(Collectors.toList());
        return commentDtoList;
    }
}
