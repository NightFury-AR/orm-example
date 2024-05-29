package com.nightfury.ormexample.ono2one.repository;

import com.nightfury.ormexample.ono2one.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneToOneRepository extends JpaRepository<Person,Long> {
}
