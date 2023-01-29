package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class ConsumerView {
    @JsonProperty
    private Long id;
    @JsonProperty(required = true)
    private String allNameConsumer;
    @JsonProperty(required = true)
    private String birthDate;
    @JsonProperty(required = true)
    private String mail;
    @JsonProperty(required = true)
    private String login;
    @JsonProperty(required = true)
    private String pass;

    @JsonProperty(defaultValue = "USER")
    private List<String> role;

}
