package com.example.imperativetoreactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration
public class PersonRoutes {
  @Bean
  public RouterFunction<ServerResponse> routes(PersonRepository repository) {
    return route()
        .path("/person", builder -> builder
            .GET("/", req -> ok().body(repository.findAll(Sort.by("lastName", "firstName"))))
            .GET("/{id}", req -> ok().body(repository.findById(Long.valueOf(req.pathVariable("id")))))
        ).build();
  }
}
