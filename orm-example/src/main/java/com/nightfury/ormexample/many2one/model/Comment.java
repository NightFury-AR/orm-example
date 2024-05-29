package com.nightfury.ormexample.many2one.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String username;
    @ManyToOne(cascade = CascadeType.ALL , optional = false)
    @JoinColumn(name = "post_id" , referencedColumnName = "id")
    @JsonIgnore
    private Post post;
}
