package ru.lernup.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lernup.bookstore.service.ControllerService;
import ru.lernup.bookstore.view.AuthorView;
import ru.lernup.bookstore.view.BookView;

import java.util.List;

@RestController
@RequestMapping("/author")
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
}
