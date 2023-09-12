package com.pumex.demo.service;

import com.pumex.demo.models.OpenAIConstants;
import com.pumex.demo.models.OpenAIRequest;
import com.pumex.demo.models.OpenAIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.pumex.demo.models.OpenAIConstants.*;

@Service
public class OpenAIService {

    @Value("${openai.api.token}")
    private String token;

    private RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenAIResponse correctSentence(String textInput) {
        ResponseEntity<OpenAIResponse> openAIResponseResponseEntity = restTemplate.exchange(OPEN_API_URL, HttpMethod.POST,
                getHttpRequestEntity(textInput), OpenAIResponse.class);
        return openAIResponseResponseEntity.getBody();
    }

    public OpenAIResponse translatedSentence(String textInput) {
        ResponseEntity<OpenAIResponse> openAIResponseResponseEntity = restTemplate.exchange(OPEN_API_URL, HttpMethod.POST,
                getHttpRequestEntityForTranslation(textInput), OpenAIResponse.class);
        return openAIResponseResponseEntity.getBody();
    }

    private HttpEntity<OpenAIRequest> getHttpRequestEntity(String text) {
        OpenAIRequest openAiRequest = new OpenAIRequest();
        openAiRequest.setModel(MODEL);
        openAiRequest.setPrompt(SPELLING_GRAMMAR_CORRECTION + text);
        openAiRequest.setFrequency_penalty(0);
        openAiRequest.setPresence_penalty(0);
        return new HttpEntity<>(openAiRequest, getHeaders());
    }

    private HttpEntity<OpenAIRequest> getHttpRequestEntityForTranslation(String text) {
        OpenAIRequest openAiRequest = new OpenAIRequest();
        openAiRequest.setModel(MODEL);
        openAiRequest.setPrompt(TRANSLATE_TO_ENGLISH + text);
        openAiRequest.setFrequency_penalty(0);
        openAiRequest.setPresence_penalty(0);
        return new HttpEntity<>(openAiRequest, getHeaders());
    }


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OpenAIConstants.AUTHORIZATION, "Bearer " + token);
        return headers;

    }
}