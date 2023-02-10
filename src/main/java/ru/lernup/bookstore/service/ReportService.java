package ru.lernup.bookstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Order;
import ru.lernup.bookstore.view.OrderView;
import ru.lernup.bookstore.view.CreateReportView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@EnableKafka
public class ReportService {
    private final DaoService daoService;

    @Autowired
    private KafkaTemplate<String, CreateReportView> kafkaTemplate;

    public ReportService(DaoService daoService) {
        this.daoService = daoService;

    }

    @KafkaListener(topics = "generateReport",containerFactory = "userKafkaListenerContainerFactory")
    public void orderListener(ConsumerRecord<String, CreateReportView> record){
        CreateReportView report= generateReport(record.key(),record.value().getMonth(),record.value().getYear());
        kafkaConsumer(record.key(),report);

    }
    private CreateReportView generateReport(String login, int month, int year){
        CreateReportView report = new CreateReportView();
        report.setName(daoService.getConsumerByLogin(login).getAllNameConsumer());
        report.setMonth(month);
        report.setYear(year);
        int cost = 0;

        List <String> url = new ArrayList<>();
        List<Order> ordersByUser = daoService.findAllOrderByUserName(login);
        List<OrderView> outList =new ArrayList<>();

        ordersByUser.forEach(order -> {
           String[] date = order.getDate().split("\\.");
            if (Integer.parseInt(date[2])==year && Integer.parseInt(date[1])==month ){
                outList.add(new OrderView(order.getId(),order.getCost(),order.getDate()));
            }
        });
        for (OrderView orderView:outList){
            url.add("http://localhost:8086/user/" + login + "/orders/"+orderView.getId());
            cost+= orderView.getCost();

        }
        report.setUrlsOrders(url);
        report.setAllCost(cost);
        return  report;
    }
    private void kafkaConsumer(String key, CreateReportView createReportView){
        kafkaTemplate.send("generatedReport",key, createReportView);
    }


}
