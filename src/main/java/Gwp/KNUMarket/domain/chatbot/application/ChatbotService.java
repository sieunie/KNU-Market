package Gwp.KNUMarket.domain.chatbot.application;

import org.springframework.http.ResponseEntity;

public interface ChatbotService {
    ResponseEntity<String> get(String question);
}
