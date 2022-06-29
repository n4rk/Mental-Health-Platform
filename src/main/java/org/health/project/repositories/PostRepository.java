package org.health.project.repositories;

import org.health.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    @Query(value="SELECT * FROM post ORDER BY date_posted DESC", nativeQuery=true)
    List<Post> getAllPosts();
}
