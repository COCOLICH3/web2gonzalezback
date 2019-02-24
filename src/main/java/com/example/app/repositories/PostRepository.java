package com.example.app.repositories;

import com.example.app.models.Post;
import com.example.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

//el jpa repository, le indico una entidad y me arma un wrapper q me permite las aplicaciones basicas sobre esa entidad