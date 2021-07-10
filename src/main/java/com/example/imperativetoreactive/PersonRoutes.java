package com.example.imperativetoreactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class PersonRoutes {
  @Bean
  public RouterFunction<ServerResponse> routes(PersonRepository repository) {
    return route()
        .path("/person", builder -> builder
            .GET("/", req -> ok().bodyValue(repository.findAll(Sort.by("lastName", "firstName"))))
            .GET("/{id}", req -> ok().bodyValue(repository.findById(Long.valueOf(req.pathVariable("id")))))
        ).build();
  }
}
