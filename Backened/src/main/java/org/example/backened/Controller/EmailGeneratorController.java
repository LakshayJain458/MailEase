package org.example.backened.Controller;

import lombok.AllArgsConstructor;

import org.example.backened.EmailRequest;
import org.example.backened.Service.EmailGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailGeneratorController {
    @Autowired
    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        String response = emailGeneratorService.EmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}
