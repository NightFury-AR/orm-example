# OneToOne

Person - Passport 

```log
Note : Defining Direction of relationship between entities has no impact on the database mapping
```

1. using foreign key
      
    - @JoinColumn is used to name the foreign key column of the child
    - here we have specified relationship in only one entity.
    - Person => Passport
    - i.e with passport data you cannot find the person

```java
@Entity
public class Person {
    @Id
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport")
    private Passport passport;
}
```

```java
@Entity
public class Passport {
    @Id
    private Long id;
    private String serialNo;
}
```

Table :

person table :

| id | name | passport |
|----|------|----------|
| 1  | A    | 1        |

passport table :

| id | serialNo      |
|----|---------------|
| 1  | zxz3sfsfv434b |

we can enable BiDirectional Mapping by

```java
import com.nightfury.ormexample.ono2one.model.Person;
import jakarta.persistence.OneToOne;

@Entity
public class Passport {
   @Id
   private Long id;
   private String serialNo;
   @OneToOne(mappedBy = "passport")
   private Person person;
}
```

2. using shared primary key 
   - in this approach , child entity can use the parent's primary key for mapping
   - so no new columns will be created :)

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "person" , cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Passport passport;
}
```

```java
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    private String serialNo;
    @OneToOne
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;
}
```

the DB tables will be , 

person:

| id | name |
|----|------|
| 1  | john |

passport :

| person_id | serial_no |
|-----------|-----------|
| 1         | zzcccxxx  |

```log
NOTE : if you are directly returning you entity to the controller, then you will get json serialization error.
see -> https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
```

3. using join table
  - this will help us to eliminate null altogether.
  - when using this approach , separate table created for primary key mappings

```java

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_passport",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "passport_id", referencedColumnName = "id")
            }
    )
    private Passport passport;
}
```

```java

@Entity
public class Passport {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String serialNo;
   @OneToOne(mappedBy = "passport")
   private Person person;
}
```

Db tables will be ,

1. person 

| id | name |
|----|------|
| 1  | John |


2. passport

| id | serial_no  |
|----|------------|
| 1  | ccvvvbbbnn |

3. person_passport

| person_id | passport_id |
|-----------|-------------|
| 1         | 1           |
