/*API Google Chat con Hooks
Name: Jorge Ivan Perez Hernandez
08/04/2025  V1.0 */
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
@RequestMapping("/api/chat")  // Configurar la ruta base para este controlador.
public class GoogleChatController {

    private static final String SECRET_KEY = "6923137204";  // Clave secreta para la autenticación.

    // Método para enviar un mensaje a Google Chat.
    @GetMapping("/send")
    public ResponseEntity<String> sendMessageToGoogleChat(
            @RequestParam String urlHook, 
            @RequestParam String message,
            @RequestParam String key) {

        // Verifica si la clave proporcionada es la correcta.
        if (!SECRET_KEY.equals(key)) {
            // Si la clave es incorrecta, devuelve una respuesta con código 401 (No autorizado).
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Clave incorrecta.");
        }

        try {
            // Crea un objeto RestTemplate para hacer la solicitud HTTP.
            RestTemplate restTemplate = new RestTemplate();
            // Crea el cuerpo de la solicitud con el mensaje a enviar.
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", message);

            // Realiza la solicitud POST al webhook de Google Chat con el mensaje.
            ResponseEntity<String> response = restTemplate.postForEntity(urlHook, requestBody, String.class);

            // Verifica si la respuesta de Google Chat fue exitosa (código 2xx).
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Mensaje enviado correctamente al bot de Google Chat.");
            } else {
                // Si la respuesta no fue exitosa, devuelve un error con el cuerpo de la respuesta de Google Chat.
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al enviar el mensaje: " + response.getBody());
            }

        } catch (Exception e) {
            // Si ocurre alguna excepción al enviar el mensaje, devuelve un error con el mensaje de la excepción.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Excepción al enviar mensaje: " + e.getMessage());
        }
    }
}
