package com.example.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Blob;
import java.util.*;

@Entity
@Table(name = "post", schema = "dbapp", catalog = "")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id", scope = Post.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String image;
    private Date creation_date;
    @JoinColumn(name = "creator_id")
    @ManyToOne
    @JsonIgnoreProperties("posts")
    private User user_creator;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("posts")
    private Collection<Wall> users = new ArrayList<>();


    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public User getUser_creator() {
        return user_creator;
    }

    public void setUser_creator(User user_creator) {
        this.user_creator = user_creator;
    }

    public Collection<Wall> getUsers() {
        return users;
    }

    public void setUsers(Collection<Wall> xxx) {
        this.users = xxx;
    }
}

//post de todos los metodos