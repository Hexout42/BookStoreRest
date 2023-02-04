package ru.lernup.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lernup.bookstore.dao.entity.*;
import ru.lernup.bookstore.dao.repository.*;


import java.util.List;


@Service
public class DaoService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final ConsumerRepository consumerRepository;

    private  final  OrderConsumerRepository orderConsumerRepository;
    private final UserRepository userRepository;


    public DaoService(AuthorRepository authorRepository,
                      BookRepository bookRepository,
                      ConsumerRepository consumerRepository,
                      OrderConsumerRepository orderConsumerRepository,
                      UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

        this.consumerRepository = consumerRepository;

        this.orderConsumerRepository = orderConsumerRepository;


        this.userRepository = userRepository;
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
  @Transactional
  public Book addBook(Book book){
        bookRepository.save(book);
        return book;
  }
  @Transactional
  public void deleteBook(Book book){
        bookRepository.delete(book);
  }
  public Consumer findConsumerById(Long id){
        return consumerRepository.getReferenceById(id);
  }
  public List<Consumer> findAllConsumer(){
        return consumerRepository.findAll();
  }
  @Transactional
  public Consumer saveUser(Consumer consumer){
        return consumerRepository.save(consumer);
  }
  public List<Order> findAllOrderByUser(Long id){
        return orderConsumerRepository.findAllByConsumer_Id(id);
  }
  public Order findOrderByConsumerIdAndId(Long id,Long consumerId){
        return orderConsumerRepository.getOrderByConsumer_IdAndId(consumerId,id);
  }
  @Transactional
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
  public User getUserByName(String name){
        return userRepository.findUserByUserName(name);
  }
@Transactional
 public Author saveAuthor(Author author){
        authorRepository.save(author);
        return author;
 }
 @Transactional
 public void deleteAuthor(Author author){
        authorRepository.delete(author);
 }

 public Consumer getConsumerByLogin(String login){
        return consumerRepository.findConsumerByUser(userRepository.findUserByUserName(login));
 }
}
