package com.nightfury.ormexample.ono2one.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_passport",
            joinColumns = {
                    @JoinColumn(name = "person_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "passport_id", referencedColumnName = "id")
            }
    )
    private Passport passport;
}
