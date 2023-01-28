package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Consumer;
import ru.lernup.bookstore.dao.entity.Order;
import ru.lernup.bookstore.dao.repository.OrderConsumerRepository;
import ru.lernup.bookstore.model.ConsumerDto;
import ru.lernup.bookstore.model.OrderDto;
import ru.lernup.bookstore.view.ConsumerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumerMapper {


    public ConsumerDto mapToDto(Consumer entity){

        return   ConsumerDto.builder().id(entity.getId())
                .allNameConsumer(entity.getAllNameConsumer())
                .birthDate(entity.getBirthDate())
                .mail(entity.getMail())
                .build();
    }
    public ConsumerView mapToView(ConsumerDto consumerDto){
        ConsumerView consumerView = new ConsumerView();
        consumerView.setAllNameConsumer(consumerDto.getAllNameConsumer());
        consumerView.setId(consumerDto.getId());
        consumerView.setMail(consumerDto.getMail());
        consumerView.setBirthDate(consumerDto.getBirthDate());
        return consumerView;
    }
    public ConsumerDto mapFromView(ConsumerView consumerView){
        return
        ConsumerDto.builder().allNameConsumer(consumerView.getAllNameConsumer())
                .id(consumerView.getId())
                .mail(consumerView.getMail())
                .birthDate(consumerView.getBirthDate())
                .build();
    }
    public Consumer mapFromDto(ConsumerDto consumerDto){
        Consumer consumer = new Consumer();
        consumer.setId(consumerDto.getId());
        consumer.setMail(consumerDto.getMail());
        consumer.setAllNameConsumer(consumerDto.getAllNameConsumer());
        consumer.setBirthDate(consumerDto.getBirthDate());
        return consumer;
    }
    }
