# ManyToOne / OneToMany

 - Post => Comment(s)

1. using foreign key (one directional)
    - if we didn't specify anything then , by default new table will be created for mapping

entities: 

```java
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // other fields goes here
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
}
```

```java
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String username;
}
```

db tables :

posts:

| ID | POST      | USERNAME |
|----|-----------|----------|
| 1  | blah blah | X        |

comments:

| ID | COMMENT | USERNAME |
|----|---------|----------|
| 1  | comment | Y        |

post_comments:

| POST_ID | COMMENT_ID |
|---------|------------|
| 1       | 1          |
| 1       | 2          |

2. using foreign key for bidirectional mapping:

- in this case , the additional table won't be created, however we need to take care of some manual mapping

entities:

```java

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String post;
    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<Comment> comments;
}
```

```java
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String username;
    @ManyToOne(cascade = CascadeType.ALL , optional = false)
    @JoinColumn(name = "post_id" , referencedColumnName = "id")
    private Post post;
}
```

Additionally , we have to map them before saving 

```java
public Post save(Post post) {
        post.getComments().forEach(comment -> comment.setPost(post));
        return repository.save(post);
}
```

tables :

posts:

| ID | POST      | USERNAME |
|----|-----------|----------|
| 1  | blah blah | X        |

comments:

| ID | COMMENT | USERNAME | POST_ID |
|----|---------|----------|---------|
| 1  | comment | Y        | 1       |
