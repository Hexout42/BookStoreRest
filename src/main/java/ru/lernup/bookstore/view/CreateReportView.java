package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data@NoArgsConstructor
@AllArgsConstructor
public class CreateReportView implements Serializable {
    @JsonProperty
    private String name;
    @JsonProperty
    private int month;
    @JsonProperty
    private int year;
    @JsonProperty
    private List<String> urlsOrders;
    @JsonProperty
    private Integer allCost;

}
