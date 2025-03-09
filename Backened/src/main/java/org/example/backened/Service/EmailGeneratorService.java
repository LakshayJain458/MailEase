package org.example.backened.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backened.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String apiUrl;
    @Value("${gemini.api.key}")
    private String apiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String EmailReply(EmailRequest emailRequest) {
        String prompt = buildPrompt(emailRequest);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );
        String response = webClient.post()
                .uri(apiUrl + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extract(response);
    }

    private String extract(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "error processing response" + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a simple and direct email:\n\n");

        if (emailRequest.getEmailContent() != null && !emailRequest.getEmailContent().isEmpty()) {
            prompt.append("- Email Content: ").append(emailRequest.getEmailContent()).append("\n");
        }

        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("- Tone: ").append(emailRequest.getTone()).append("\n");
        } else {
            prompt.append("- Tone: Friendly and casual.\n");
        }

        prompt.append("\nEnsure the email is clear and concise.\n");
        prompt.append("Only include essential details without unnecessary explanations.\n");

        // Example to guide AI
        prompt.append("\nExample Format:\n");
        prompt.append("Subject: Catching Up\n");
        prompt.append("Hi Alex,\n\n");
        prompt.append("I hope you're doing well. It's been a while since we last spoke, and I wanted to check in.\n");
        prompt.append("Let’s catch up sometime soon—let me know when you're free!\n\n");
        prompt.append("Best,\nJordan\n");

        return prompt.toString();
    }


}
