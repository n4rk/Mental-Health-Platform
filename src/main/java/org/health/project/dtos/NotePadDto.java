package org.health.project.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NotePadDto {
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Long id;
        private String title;
        private Date dateCreated;
        private String content;
}
