package com.aicopilot.rag;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DocumentIngestionService {

    private final EmbeddingService embeddingService;
    private final JdbcTemplate jdbcTemplate;

    public DocumentIngestionService(EmbeddingService embeddingService,
                                    JdbcTemplate jdbcTemplate) {
        this.embeddingService = embeddingService;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void ingest(String text) {

        String[] chunks = text.split("(?<=\\. )");

        for (String chunk : chunks) {

            float[] embedding = embeddingService.generateEmbedding(chunk);

            String vector = Arrays.toString(embedding);

            jdbcTemplate.update("""
                INSERT INTO document_chunks (content, embedding)
                VALUES (?, ?::vector)
                """, chunk, vector);
        }
    }
}