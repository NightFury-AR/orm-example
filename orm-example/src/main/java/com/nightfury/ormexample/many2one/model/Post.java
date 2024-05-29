package com.nightfury.ormexample.many2one.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String post;
    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<Comment> comments;
}
