package com.aicopilot.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private final EmbeddingService embeddingService;
    private final VectorSearchService vectorSearchService;
    private final ChatClient chatClient;

    public RagService(EmbeddingService embeddingService,
                      VectorSearchService vectorSearchService,
                      ChatClient.Builder builder) {

        this.embeddingService = embeddingService;
        this.vectorSearchService = vectorSearchService;
        this.chatClient = builder.build();
    }

    public String ask(String question) {

        float[] embedding = embeddingService.generateEmbedding(question);

        List<String> docs = vectorSearchService.searchSimilar(embedding);

        String context = String.join("\n", docs);

        String prompt = """
        Use the following context to answer the question.

        Context:
        %s

        Question:
        %s
        """.formatted(context, question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}