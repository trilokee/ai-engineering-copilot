package com.aicopilot.rag;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorSearchService {

    private final JdbcTemplate jdbcTemplate;

    public VectorSearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> searchSimilar(float[] embedding) {

        String sql = """
        SELECT content
        FROM document_chunks
        WHERE embedding IS NOT NULL
        ORDER BY embedding <-> ?::vector
        LIMIT 5
        """;

        String vector = java.util.Arrays.toString(embedding);

        return jdbcTemplate.queryForList(sql, String.class, vector);
    }
}