package org.health.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PostDto {
    private String id;
    private String username;    //this is the anonymous username for the posts
    private Date datePosted;
    private String content;
}
