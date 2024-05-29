package com.nightfury.ormexample.controller;

import com.nightfury.ormexample.many2many.model.Customer;
import com.nightfury.ormexample.many2many.repository.Many2ManyRepository;
import com.nightfury.ormexample.many2one.model.Post;
import com.nightfury.ormexample.many2one.repository.ManyToOneRepository;
import com.nightfury.ormexample.ono2one.model.Passport;
import com.nightfury.ormexample.ono2one.model.Person;
import com.nightfury.ormexample.ono2one.repository.OneToOneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class JpaController {

    @Autowired
    private OneToOneRepository oneToOneRepository;

    @Autowired
    private ManyToOneRepository manyToOneRepository;

    @Autowired
    private Many2ManyRepository many2ManyRepository;

    @PostMapping("/one2one")
    public Person one2one(@RequestBody Person person) {
        return this.oneToOneRepository.save(person);
    }

    @PostMapping("/many2one")
    public Post many2one(@RequestBody Post post) {
        post.getComments().forEach(comment -> comment.setPost(post));
        return this.manyToOneRepository.save(post);
    }

    @PostMapping("/many2many")
    public Customer many2many(Customer customer) {
        return this.many2ManyRepository.save(customer);
    }

}
