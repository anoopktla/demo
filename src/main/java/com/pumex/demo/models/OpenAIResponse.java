package com.pumex.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class OpenAIResponse {
    private String id;
    private String object;
    private Date creation;
    private String model;
    @JsonProperty("choices")
    private List<Choices> choicesList;
    @JsonProperty("usage")
    private Usage usage;

}
