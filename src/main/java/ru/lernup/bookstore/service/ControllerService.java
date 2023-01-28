package ru.lernup.bookstore.service;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Consumer;
import ru.lernup.bookstore.dao.entity.DetailsOrder;
import ru.lernup.bookstore.dao.entity.Order;
import ru.lernup.bookstore.mapper.*;
import ru.lernup.bookstore.view.*;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ControllerService {
    private final DaoService daoService;
    private final ConsumerMapper consumerMapper;
    private final OrderMapper orderMapper;
    private final DetailsOrderMapper detailOrder;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public ControllerService(DaoService daoService,
                             ConsumerMapper consumerMapper,
                             OrderMapper orderMapper,
                             DetailsOrderMapper detailOrder,
                             AuthorMapper authorMapper,
                             BookMapper bookMapper) {
        this.daoService = daoService;
        this.consumerMapper = consumerMapper;
        this.orderMapper = orderMapper;
        this.detailOrder = detailOrder;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    public ConsumerView getConsumer(Long id){

        return  consumerMapper.mapToView(
                consumerMapper.mapToDto(daoService.findConsumerById(id)));
    }
    public List<ConsumerView> getAllConsumer(){
        return daoService.findAllConsumer().stream()
                .map(consumerMapper::mapToDto)
                .collect(Collectors.toList())
                .stream().map(consumerMapper::mapToView)
                .collect(Collectors.toList());
    }
    public ConsumerView createUser(ConsumerView consumer){
        daoService.saveUser(
                consumerMapper.mapFromDto(consumerMapper.mapFromView(consumer)));
        return consumer;
    }
    public ConsumerView updateUser(ConsumerView consumer){
        Consumer user=
                daoService.findConsumerById(consumer.getId());
        Consumer userNew=
                consumerMapper.mapFromDto(consumerMapper.mapFromView(consumer));
        if (!(user.getMail().equals(userNew.getMail()))){
            user.setMail(userNew.getMail());
        }
        if (!(user.getAllNameConsumer().equals(userNew.getAllNameConsumer()))){
            user.setAllNameConsumer(userNew.getAllNameConsumer());
        }
        if(!(user.getBirthDate().equals(userNew.getBirthDate()))){
            user.setBirthDate(userNew.getBirthDate());
        }
        daoService.saveUser(user);
        return consumerMapper.mapToView(consumerMapper.mapToDto(user));

    }
    public List<OrderView> getAllOrdersByUser(Long id){
        return daoService.findAllOrderByUser(id).stream().map(order -> {
            return orderMapper.mappedToView(orderMapper.mappedToDto(order));
        }).collect(Collectors.toList());
    }
    public List<DetailsOrderView> DetailsOrder(Long idConsumer, Long idOrder){
        List<DetailsOrder> detailsOrder=
        daoService.findOrderByConsumerIdAndId(idConsumer,idConsumer).getDetailsOrders();
       return detailsOrder.stream().map(detailsOrder1 -> {
           return
           detailOrder.mappedToView(detailOrder.mappedToDto(detailsOrder1));
       }).collect(Collectors.toList());
    }
    public OrderView createOrder(Long idConsumer,OrderView orderView){
        Order order = new Order();
        order = orderMapper.mappedFromDto(orderMapper.mappedFromView(orderView));
        order.setConsumer(daoService.findConsumerById(idConsumer));
        daoService.saveOrder(order);
        return orderView;
    }
    public List<OrderView> getAllOrder(){
        return
        daoService.getAllOrder().stream().map(order -> {
           return orderMapper.mappedToView(orderMapper.mappedToDto(order));
        }).collect(Collectors.toList());
    }
    public List<AuthorView> getAllAuthor(){
        return
                daoService.getAllAuthor().stream().map((author -> {
                    return  authorMapper.mappedToView(authorMapper.mappedToDto(author));
                })).collect(Collectors.toList());
    }
    public AuthorView getAuthorById(Long id){
        return authorMapper.mappedToView(authorMapper.mappedToDto(daoService.getAuthorById(id)));
    }
    public BookView getBookById(Long id){
        return  bookMapper.mappedToView(bookMapper.mappedToDto(daoService.findBookById(id)));
    }
    public List<BookView> getAllBook(){
        return daoService.findAllBook().stream().map(book -> {
           return bookMapper.mappedToView(bookMapper.mappedToDto(book));
        }).collect(Collectors.toList());
    }
}
