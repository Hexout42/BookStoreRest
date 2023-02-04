package ru.lernup.bookstore.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.bookstore.service.ControllerService;
import ru.lernup.bookstore.view.AuthorView;
import ru.lernup.bookstore.view.BookView;

import java.util.List;

@RestController
@RequestMapping("/author")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")

public class AuthorController {
    private final ControllerService controllerService;

    public AuthorController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping
    public List<AuthorView> getAllAuthor(){
        return controllerService.getAllAuthor();
    }
    @GetMapping("/{id}")
    public AuthorView getAuthorById(@PathVariable("id") Long id){
        return controllerService.getAuthorById(id);
    }
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id){
    controllerService.deleteAuthor(id);
    }
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @PostMapping
    public AuthorView addAuthor(@RequestBody AuthorView authorView){
        return controllerService.addAuthor(authorView);
    }
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @PutMapping
    public AuthorView updateAuthor(@RequestBody AuthorView authorView){
        return controllerService.updateAuthor(authorView);
    }

}
