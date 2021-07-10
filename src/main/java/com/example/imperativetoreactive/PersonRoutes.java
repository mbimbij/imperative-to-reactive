package com.example.imperativetoreactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class PersonRoutes {
  @Bean
  public RouterFunction<ServerResponse> routes(Handler handler) {
    return route()
        .path("/person", builder -> builder
            .GET("", handler::findAll)
            .GET("/{id}", handler::findById)
        ).build();
  }

  @Bean
  public PersonRoutes.Handler personRoutesHandler(PersonRepository repository){
     return new Handler(repository);
  }

  public static class Handler{
    private final PersonRepository repository;

    public Handler(PersonRepository repository) {
      this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
      Flux<Person> all = repository.findAll(Sort.by("lastName", "firstName"));
      return ok().body(fromPublisher(all, Person.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request){
      Mono<Person> person = repository.findById(Long.valueOf(request.pathVariable("id")));
      return ok().body(fromPublisher(person, Person.class));
    }
  }
}
