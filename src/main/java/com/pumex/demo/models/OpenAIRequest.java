package com.pumex.demo.models;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIRequest {
    private String model;
    private String prompt;
    private Integer temperature;
    private Integer max_tokens;
    private Integer top_p;
    private Integer frequency_penalty;
    private Integer presence_penalty;
    private List<String> stop;
}
