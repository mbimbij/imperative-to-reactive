package com.example.imperativetoreactive;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonController {
  private final PersonRepository personRepository;

  @GetMapping("/person")
  public List<Person> findAll(){
    return personRepository.findAll(Sort.by("lastName", "firstName"));
  }

  @GetMapping("/person/{id}")
  public Optional<Person> findById(@PathVariable long id){
    return personRepository.findById(id);
  }
}
