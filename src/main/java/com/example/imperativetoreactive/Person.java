package com.example.imperativetoreactive;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@Data
@Cacheable
@Cache(region = "database", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person {
  @Id
  @GeneratedValue
  private long id;
  private String firstName;
  private String lastName;
  private String birthDate;
}
