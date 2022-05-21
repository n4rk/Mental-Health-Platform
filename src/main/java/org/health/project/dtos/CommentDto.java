package org.health.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private Long id;
    private Date date_posted;
    private String content;
    private PostDto postDto;
}
