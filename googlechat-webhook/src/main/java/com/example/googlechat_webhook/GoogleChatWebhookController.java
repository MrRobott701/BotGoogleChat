package com.example.googlechat_webhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class GoogleChatWebhookController {

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String webhookUrl, 
                                              @RequestParam String message) {
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, String> body = new HashMap<>();
        body.put("text", message);
        
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);
        
        return ResponseEntity.ok(response.getBody());
    }
}
