package ru.lernup.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lernup.bookstore.service.ControllerService;
import ru.lernup.bookstore.view.BookView;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private  final ControllerService controllerService;

    public BookController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }
    @GetMapping
    public List<BookView> getAllBook(){
        return controllerService.getAllBook();
    }
    @GetMapping("/{id}")
    public  BookView getBookById(@PathVariable("id") Long id){
        return controllerService.getBookById(id);
    }
}
