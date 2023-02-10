package ru.lernup.bookstore.repository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;
import ru.lernup.bookstore.view.CreateReportView;

import java.util.HashMap;
import java.util.Map;
@Repository
@EnableKafka
public class ReportRepository {
    Map<String, CreateReportView> allReports = new HashMap<>();
    public ReportRepository (){

    }
    @KafkaListener(topics = "generatedReport",containerFactory = "userKafkaListenerContainerFactory")
    public void msgListener(ConsumerRecord<String, CreateReportView> record){
        String key = record.key()+record.value().getMonth() + record.value().getYear();
        allReports.put(key,record.value());

    }
    public CreateReportView getReport(String login,int month,int year){
        return  allReports.get(login+month+year);
    }


}
