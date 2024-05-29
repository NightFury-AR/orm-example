package com.nightfury.ormexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightfury.ormexample.many2many.model.Customer;
import com.nightfury.ormexample.many2many.model.Product;
import com.nightfury.ormexample.many2one.model.Comment;
import com.nightfury.ormexample.many2one.model.Post;
import com.nightfury.ormexample.ono2one.model.Passport;
import com.nightfury.ormexample.ono2one.model.Person;

import java.util.List;

public class Testmain {
    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setName("ABC");
        Passport passport = new Passport();
        passport.setSerialNo("zzzzxxxxcccc");
        person.setPassport(passport);
        System.out.println(new ObjectMapper().writeValueAsString(person));
        Post post = new Post();
        post.setPost("post");
        post.setUsername("post_owner");
        Comment comment = new Comment();
        comment.setUsername("comment_owner");
        comment.setComment("comment");
        Comment comment1 = new Comment();
        comment.setUsername("comment_owner1");
        comment.setComment("comment1");
        post.setComments(List.of(comment,comment1));
        System.out.println(new ObjectMapper().writeValueAsString(post));
        Product photo = Product.builder()
                .count(5)
                .name("photo")
                .build();
        Product shirt = Product.builder()
                .count(5)
                .name("shirt")
                .build();
        Customer customer = Customer.builder()
                .name("customer")
                .email("c@g.com")
                .products(List.of(photo, shirt))
                .build();
        System.out.println(new ObjectMapper().writeValueAsString(customer));
    }
}
