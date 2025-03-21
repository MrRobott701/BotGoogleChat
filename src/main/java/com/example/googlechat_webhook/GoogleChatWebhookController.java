//Corre el proyecto con mvn spring-boot:run
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
@RequestMapping("/webhook") // Ruta base para los métodos del controlador
public class GoogleChatWebhookController {

    //Método que maneja las solicitudes POST en la ruta /send
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String webhookUrl, 
                                              @RequestParam String message) {
        
        // Crea una instancia de RestTemplate para realizar solicitudes HTTP
        RestTemplate restTemplate = new RestTemplate();  
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Crea un mapa para almacenar los datos del cuerpo de la solicitud
        Map<String, String> body = new HashMap<>();
        // Coloca el mensaje dentro del cuerpo de la solicitud
        body.put("text", message);
        
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        
        // Envía la solicitud POST a la URL proporcionada utilizando RestTemplate
        // El método postForEntity envía la solicitud y obtiene una respuesta
        ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);
        
        // Devuelve la respuesta recibida, contenida en el cuerpo de la respuesta
        return ResponseEntity.ok(response.getBody());
    }
}