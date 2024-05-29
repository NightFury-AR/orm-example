package com.nightfury.ormexample.many2many.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order",
            joinColumns = @JoinColumn(
                    name = "customer_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName ="id"
            )
    )
    private List<Product> products;

}
