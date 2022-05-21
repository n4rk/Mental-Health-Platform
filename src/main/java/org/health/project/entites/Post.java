package org.health.project.entites;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id
    private String id;
    private Date datePosted;
    private String content;
    @OneToMany(mappedBy = "post")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<Comment> commentList = new ArrayList<>();
}
