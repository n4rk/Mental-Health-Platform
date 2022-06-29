package org.health.project.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id
    private String id;
    private String username;
    private Date datePosted;
    @Column(length = 1000)
    private String content;
    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<Comment> commentList = new ArrayList<>();
}
