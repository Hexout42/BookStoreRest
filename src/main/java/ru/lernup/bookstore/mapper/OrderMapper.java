package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Order;
import ru.lernup.bookstore.model.OrderDto;
import ru.lernup.bookstore.view.OrderView;

@Component
public class OrderMapper {
    public OrderDto mappedToDto(Order order){
        return OrderDto.builder()
                .cost(order.getCost())
                .date(order.getDate())
                .id(order.getId())
                .build();
    }
    public OrderView mappedToView(OrderDto orderDto){
        OrderView orderView = new OrderView();
        orderView.setDate(orderDto.getDate());
        orderView.setId(orderDto.getId());
        orderView.setCost(orderDto.getCost());
        return orderView;
    }
    public OrderDto mappedFromView(OrderView orderView){
        return OrderDto.builder()
                        .cost(orderView.getCost())
                                .date(orderView.getDate())
                                        .id(orderView.getId()).build();
    }
    public Order mappedFromDto(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDate(orderDto.getDate());
        order.setCost(orderDto.getCost());
        return order;

    }
}
