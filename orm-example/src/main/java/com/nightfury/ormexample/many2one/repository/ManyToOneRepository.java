package com.nightfury.ormexample.many2one.repository;

import com.nightfury.ormexample.many2one.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManyToOneRepository extends JpaRepository<Post,Long> {
}
