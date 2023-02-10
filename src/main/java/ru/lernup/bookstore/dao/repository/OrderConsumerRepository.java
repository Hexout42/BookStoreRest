package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.Order;

import java.util.List;


public interface OrderConsumerRepository extends JpaRepository<Order, Long> {
   Order getOrderById(Long id);
   List<Order> findAllByConsumer_Id(Long id);
   Order getOrderByConsumer_IdAndId(Long idConsumer,Long id );


}