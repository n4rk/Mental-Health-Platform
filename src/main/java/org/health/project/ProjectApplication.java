package org.health.project;

import org.health.project.dtos.CommentDto;
import org.health.project.dtos.NotePadDto;
import org.health.project.dtos.PostDto;
import org.health.project.entities.Post;
import org.health.project.mappers.PostMapper.PostMapper;
import org.health.project.mappers.commentMapper.CommentMapper;
import org.health.project.security.entities.AppRole;
import org.health.project.security.entities.AppUser;
import org.health.project.security.service.SecurityService;
import org.health.project.services.NotePadService.NotePadService;
import org.health.project.services.commentService.CommentService;
import org.health.project.services.postService.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }


    //@Bean
    CommandLineRunner start(NotePadService notePadService,
                            PostService postService,
                            CommentService commentService,
                            PostMapper postMapper,
                            CommentMapper commentMapper){
        return args -> {
            for (int i = 0; i < 5; i++) {
                PostDto postDto = new PostDto();
                postDto.setContent(
                        "Integer mattis iaculis libero sed pellentesque. Nullam tincidunt id turpis vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam accumsan nibh vel pretium molestie."
                                +i);
                PostDto savePostDto = postService.savePost(postDto);
                //postService.savePost(postDto);
                Post post = postMapper.fromPostDtoToPost(savePostDto);

                for (int j = 0; j < 3; j++) {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setDate_posted(new Date());
                    commentDto.setContent("Proin justo magna, volutpat ut tortor suscipit, tempus malesuada risus. Sed vitae enim neque. Donec vitae semper odio. Vivamus luctus magna tellus. Nunc fermentum ex ac turpis varius porttitor.");
                    commentDto.setPostDto(postDto);
                    commentService.saveComment(commentDto);
                }

            }


            for (int i = 0; i < 5; i++) {
                NotePadDto notePadDto = new NotePadDto();
                notePadDto.setTitle("Neque porro quisquam est qui dolorem ipsum "+i);
                notePadDto.setDateCreated(new Date());
                notePadDto.setContent("Nunc eget eros quis diam condimentum condimentum a vitae diam. Aliquam erat volutpat. Sed massa ipsum, mollis ac venenatis ac, congue eget tellus.");
                notePadService.saveNote(notePadDto);
            }


        };
    }


    //@Bean
    CommandLineRunner users(SecurityService securityService) {
        return args -> {
            securityService.addNewRole(new AppRole(null, "USER"));
            securityService.addNewRole(new AppRole(null, "ADMIN"));

            securityService.addNewUser(new AppUser(null, "user", "test", new ArrayList<>()));
            securityService.addNewUser(new AppUser(null, "admin", "test", new ArrayList<>()));

            securityService.addRoleToUser("user", "USER");
            securityService.addRoleToUser("admin", "USER");
            securityService.addRoleToUser("admin", "ADMIN");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
