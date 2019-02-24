package com.example.app.repositories;

import com.example.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE username=:username and password=:password limit 1", nativeQuery = true)
    List<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

//findby y una propiedad del user, me la busca automaticamente
// la query = busca un usuario con ese nombre y pass con ese pass para poder loggearse