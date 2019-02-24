package com.example.app.repositories;

import com.example.app.models.User;
import com.example.app.models.Wall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallRepository extends JpaRepository<Wall, Long> {
    //    List<User> findByEmail(String email);
//    List<User> findByUsername(String username);
//
//    @Query(value = "DELETE  FROM wall  WHERE post_id = :postId ", nativeQuery = true)
    void deleteByPostId(@Param("post_id") long postId);
}
