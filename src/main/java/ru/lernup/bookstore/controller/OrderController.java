package ru.lernup.bookstore.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lernup.bookstore.service.ControllerService;
import ru.lernup.bookstore.view.OrderView;

import java.util.List;

@RestController
@RequestMapping("/orders")
@PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class OrderController {
    private final ControllerService controllerService;

    public OrderController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }
@GetMapping
    public List<OrderView> getAll(){
        return controllerService.getAllOrder();
    }

}
