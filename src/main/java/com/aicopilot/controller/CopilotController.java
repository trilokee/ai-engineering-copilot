package com.aicopilot.controller;

import com.aicopilot.service.CopilotService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/copilot")
public class CopilotController {

    private final CopilotService copilotService;

    public CopilotController(CopilotService copilotService) {
        this.copilotService = copilotService;
    }

    @PostMapping("/ask")
    public Map<String, String> ask(@RequestBody Map<String,String> request) {

        String question = request.get("question");

        String response = copilotService.ask(question);

        return Map.of(
                "question", question,
                "answer", response
        );
    }


}