package ru.lernup.bookstore.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.bookstore.repository.ReportRepository;
import ru.lernup.bookstore.service.ControllerService;

import ru.lernup.bookstore.view.ConsumerView;
import ru.lernup.bookstore.view.DetailsOrderView;
import ru.lernup.bookstore.view.OrderView;
import ru.lernup.bookstore.view.CreateReportView;

import java.util.List;

@RestController
@RequestMapping("/user")
@EnableKafka
public class UserController {
   private final ControllerService controllerService;
   private boolean verification=false;
   private final ReportRepository repository;


    @Autowired
    private KafkaTemplate<String, CreateReportView> kafkaTemplate;



    public UserController(ControllerService controllerService,
                          ReportRepository repository) {
        this.controllerService = controllerService;

        this.repository = repository;
    }
    @GetMapping("{id}")
    public ConsumerView getUser(@PathVariable("id") Long id){

         return controllerService.getConsumer(id);
    }
    @GetMapping
    public List<ConsumerView> getAllUser(){

        return controllerService.getAllConsumer();
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


    @PutMapping("{id}")
    @PreAuthorize("#consumer.login==authentication.name")
    public ConsumerView updateUser(@PathVariable("id") Long id,
            @RequestBody ConsumerView consumer
    ){
        consumer.setId(id);
        ConsumerView consumerView =controllerService.getConsumer(id);
        if (consumer.getLogin().equals(consumerView.getLogin())){

        if (!(consumerView.getMail().equals(consumer.getMail())))
            while (true){
                if (verification) {
                    verification = false;
                    return controllerService.updateUser(consumer);
                }
            }


        return controllerService.updateUser(consumer);}
        return null;
    }
    @PreAuthorize("#login==authentication.name")

    @GetMapping("{login}/orders")
    public List<OrderView> allOrderToConsumer(@PathVariable("login") String login){
      ConsumerView consumerView = controllerService.getConsumerByLogin(login);
       return controllerService.getAllOrdersByUser(consumerView.getId());
    }
    @PreAuthorize("#login==authentication.name")

    @GetMapping("{id}/orders/{order_id}")
    public List<DetailsOrderView> detailsOrder(@PathVariable("id") String login,
                                               @PathVariable("order_id") Long orderId){
        ConsumerView consumerView = controllerService.getConsumerByLogin(login);
     return controllerService.DetailsOrder(consumerView.getId(),orderId);
    }
    @PreAuthorize("#login==authentication.name")
    @PostMapping("{id}/orders")
    public OrderView createOrder(@PathVariable("id") String login,
                                 @RequestBody OrderView orderView){
        ConsumerView consumerView = controllerService.getConsumerByLogin(login);
        return controllerService.createOrder(consumerView.getId(),orderView);
    }
    @PreAuthorize("#login==authentication.name")
    @PutMapping("{id}/orders")
    public OrderView updateOrder(@PathVariable("id") String login,
                                 @RequestBody OrderView order){
        ConsumerView consumerView = controllerService.getConsumerByLogin(login);
        return controllerService.updateOrder(consumerView.getId(),order);
    }
    @PreAuthorize("#login==authentication.name")
    @PostMapping("{id}/orders/generateReport")
    public void generateReport(@PathVariable("id") String login,
                                   @RequestBody CreateReportView createReportView){

      kafkaTemplate.send("generateReport",login, createReportView);
    }
    @PreAuthorize("#login==authentication.name")
    @GetMapping("{id}/orders/generateReport")
    public CreateReportView getGenerateReport(@PathVariable("id") String login,
                                              @RequestParam(name="month")Integer month,
                                              @RequestParam(name="year") Integer year){
      return   repository.getReport(login,month,year);
    }


}
