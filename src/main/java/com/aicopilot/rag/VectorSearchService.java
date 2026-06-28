package com.aicopilot.rag;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VectorSearchService {

    private final JdbcTemplate jdbcTemplate;

    public VectorSearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> searchSimilar(float[] embedding) {

        String sql = "SELECT content\n" +
                     "FROM document_chunks\n" +
                     "WHERE embedding IS NOT NULL\n" +
                     "ORDER BY embedding <-> ?::vector\n" +
                     "LIMIT 5\n";

        String vector = Arrays.toString(embedding);

        return jdbcTemplate.queryForList(sql, String.class, vector);
    }
}