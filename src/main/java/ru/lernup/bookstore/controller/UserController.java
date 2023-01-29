package ru.lernup.bookstore.controller;

import org.springframework.web.bind.annotation.*;
import ru.lernup.bookstore.service.ControllerService;

import ru.lernup.bookstore.view.ConsumerView;
import ru.lernup.bookstore.view.DetailsOrderView;
import ru.lernup.bookstore.view.OrderView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   private final ControllerService controllerService;
   private boolean verification=false;
   private String url;


    public UserController( ControllerService controllerService) {
        this.controllerService = controllerService;

    }
    @GetMapping("{id}")
    public ConsumerView getUser(@PathVariable("id") Long id){

         return controllerService.getConsumer(id);
    }
    @GetMapping
    public List<ConsumerView> getAllUser(){

        return controllerService.getAllConsumer();
    }
    @GetMapping("/register")
    public String register(){
        return "please register";
    }
    @PostMapping("/verification")
    public Boolean verification(){
        verification =true;
        return true;
    }
    @PostMapping("/register")
    public ConsumerView createUser(
            @RequestBody ConsumerView consumer
    ){
       System.out.println("Start register" + consumer.getAllNameConsumer());
        while (true) {
            if (verification) {
                verification=false;

                 return controllerService.createUser(consumer);

            }
        }

    }


    @PutMapping
    public ConsumerView updateUser(
            @RequestBody ConsumerView consumer
    ){
        ConsumerView consumerView =controllerService.getConsumer(consumer.getId());
        if (!(consumerView.getMail().equals(consumer.getMail())))
            while (true){
                if (verification) {
                    verification = false;
                    return controllerService.updateUser(consumer);
                }
            }

        return controllerService.updateUser(consumer);
    }
    @GetMapping("{id}/orders")
    public List<OrderView> allOrderToConsumer(@PathVariable("id") Long id){
       return controllerService.getAllOrdersByUser(id);
    }
    @GetMapping("{id}/orders/{order_id}")
    public List<DetailsOrderView> detailsOrder(@PathVariable("id") Long consumerId,
                                               @PathVariable("order_id") Long orderId){
     return controllerService.DetailsOrder(consumerId,orderId);
    }
    @PostMapping("{id}/orders")
    public OrderView createOrder(@PathVariable("id") Long idUser,
                                 @RequestBody OrderView orderView){
        return controllerService.createOrder(idUser,orderView);
    }


}
