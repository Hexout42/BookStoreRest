package ru.lernup.bookstore.service;

import org.springframework.stereotype.Service;
import ru.lernup.bookstore.dao.entity.Author;
import ru.lernup.bookstore.dao.entity.Book;
import ru.lernup.bookstore.dao.entity.Consumer;
import ru.lernup.bookstore.dao.entity.Order;
import ru.lernup.bookstore.dao.repository.*;
import ru.lernup.bookstore.mapper.ConsumerMapper;
import ru.lernup.bookstore.model.ConsumerDto;
import ru.lernup.bookstore.view.ConsumerView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DaoService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final ConsumerRepository consumerRepository;

    private  final  OrderConsumerRepository orderConsumerRepository;
    private final ConsumerMapper consumerMapper;


    public DaoService(AuthorRepository authorRepository,
                      BookRepository bookRepository,
                      ConsumerRepository consumerRepository,
                      OrderConsumerRepository orderConsumerRepository,
                      ConsumerMapper consumerMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

        this.consumerRepository = consumerRepository;

        this.orderConsumerRepository = orderConsumerRepository;

        this.consumerMapper = consumerMapper;
    }

  public Order findOrderById(Long id){

        return orderConsumerRepository.getOrderById(id);
  }
  public Book findBookById(Long id){

        return bookRepository.findBooksById(id);
  }
  public List<Book> findAllBook(){
        return bookRepository.findAll();
  }
  public Consumer findConsumerById(Long id){
        return consumerRepository.getReferenceById(id);
  }
  public List<Consumer> findAllConsumer(){
        return consumerRepository.findAll();
  }
  public Consumer saveUser(Consumer consumer){
        return consumerRepository.save(consumer);
  }
  public List<Order> findAllOrderByUser(Long id){
        return orderConsumerRepository.findAllByConsumer_Id(id);
  }
  public Order findOrderByConsumerIdAndId(Long id,Long consumerId){
        return orderConsumerRepository.getOrderByConsumer_IdAndId(consumerId,id);
  }
  public Order saveOrder(Order order){
        orderConsumerRepository.save(order);
        return order;
  }
  public List<Order> getAllOrder(){
        return orderConsumerRepository.findAll();
  }
  public List<Author> getAllAuthor(){
        return authorRepository.findAll();
  }
  public Author getAuthorById(Long id){
        return authorRepository.getReferenceById(id);
  }


}
