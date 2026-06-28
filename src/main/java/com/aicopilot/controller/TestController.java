package com.aicopilot.controller;

import com.aicopilot.rag.RagService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    private final JdbcTemplate jdbcTemplate;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return "API working";
    }

    @GetMapping("/db-info")
    public Map<String, String> dbInfo() {
        String db = jdbcTemplate.queryForObject(
                "select current_database()",
                String.class);

        String schema = jdbcTemplate.queryForObject(
                "select current_schema()",
                String.class);

        return Map.of(
                "database", db,
                "schema", schema
        );
    }
}
