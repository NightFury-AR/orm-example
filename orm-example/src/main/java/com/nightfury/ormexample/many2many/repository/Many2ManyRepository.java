package com.nightfury.ormexample.many2many.repository;

import com.nightfury.ormexample.many2many.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Many2ManyRepository extends JpaRepository<Customer,Long> {
}
