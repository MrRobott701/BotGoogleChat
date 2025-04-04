package com.Webhooks.BotWebhook;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/chat")
public class GoogleChatController {

    private static final String SECRET_KEY = "6923137204";

    @GetMapping("/send")
    public ResponseEntity<String> sendMessageToGoogleChat(
            @RequestParam String urlHook,
            @RequestParam String message,
            @RequestParam String key) {

        if (!SECRET_KEY.equals(key)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Clave incorrecta.");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", message);

            ResponseEntity<String> response = restTemplate.postForEntity(urlHook, requestBody, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Mensaje enviado correctamente al bot de Google Chat.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al enviar el mensaje: " + response.getBody());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Excepción al enviar mensaje: " + e.getMessage());
        }
    }
}
