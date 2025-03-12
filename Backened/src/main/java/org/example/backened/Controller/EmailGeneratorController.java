package org.example.backened.Controller;

import lombok.AllArgsConstructor;

import org.example.backened.EmailRequest;
import org.example.backened.Service.EmailGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailGeneratorController {
    @Autowired
    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        String response = emailGeneratorService.EmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}
