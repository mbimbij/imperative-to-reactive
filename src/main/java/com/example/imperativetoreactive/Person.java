package com.example.imperativetoreactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@org.springframework.data.relational.core.mapping.Table("PERSON")
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Cache(region = "database", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person {
  @Id
  @org.springframework.data.annotation.Id
  @GeneratedValue
  private long id;
  private String firstName;
  private String lastName;
  private String birthDate;
}
