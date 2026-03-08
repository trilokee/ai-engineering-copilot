package com.aicopilot.controller;

import com.aicopilot.rag.RagService;
import com.aicopilot.service.CopilotService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/copilot")
public class CopilotController {

    private final RagService ragService;
    private final JdbcTemplate jdbcTemplate;

    public CopilotController(RagService ragService, JdbcTemplate jdbcTemplate) {
        this.ragService = ragService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/db")
    public String checkDb() {
        return jdbcTemplate.queryForObject(
                "select current_database()",
                String.class
        );
    }

    @PostMapping("/ask")
    public Map<String, String> ask(@RequestBody Map<String,String> request) {

        String question = request.get("question");
        String answer = ragService.ask(question);

        return Map.of(
                "question", question,
                "answer", answer
        );
    }
}