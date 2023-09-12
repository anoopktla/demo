package com.pumex.demo.controller;

import com.pumex.demo.models.OpenAIResponse;
import com.pumex.demo.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/demo")
@RequiredArgsConstructor
public class SentenceController {

    private final OpenAIService openAIService;

    @GetMapping("/text/correctedsentences")
    public List<String> correctSentence(@RequestParam String input) {
        OpenAIResponse response = openAIService.correctSentence(input);
        return response.getChoicesList().stream().map(c -> c.getText().strip()).collect(Collectors.toList());
    }

    @GetMapping("/text/translatedsentences")
    public List<String> translatedSentence(@RequestParam String input) {
        OpenAIResponse response = openAIService.translatedSentence(input);
        return response.getChoicesList().stream().map(c -> c.getText().strip()).collect(Collectors.toList());
    }

}