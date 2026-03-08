package com.aicopilot.rag;

public class DocumentChunk {

    private Long id;
    private String content;
    private String embedding;

    public DocumentChunk(Long id, String content, String embedding) {
        this.id = id;
        this.content = content;
        this.embedding = embedding;
    }

    public String getContent() {
        return content;
    }
}