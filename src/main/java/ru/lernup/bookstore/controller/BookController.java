package ru.lernup.bookstore.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lernup.bookstore.service.ControllerService;
import ru.lernup.bookstore.view.BookView;

import java.util.List;

@RestController
@RequestMapping("/book")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class BookController {
    private  final ControllerService controllerService;

    public BookController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }
    @GetMapping
    public Flux<BookView> getAllBook(){
        return
                Flux.fromStream(controllerService.getAllBook().stream());
    }
    @GetMapping("/{id}")
    public Mono<BookView> getBookById(@PathVariable("id") Long id){

        return Mono.just(controllerService.getBookById(id));
    }
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @PostMapping
    public Mono<BookView> addBook(@RequestBody BookView bookView){
        return Mono.just(controllerService.addBook(bookView));
    }
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        controllerService.deleteBook(id);
    }
}
