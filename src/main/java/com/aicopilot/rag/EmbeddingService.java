package com.aicopilot.rag;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] generateEmbedding(String text) {
        return embeddingModel.embed(text);
    }

    public String toPgVector(float[] embedding){

        StringBuilder sb = new StringBuilder("[");

        for(int i=0;i<embedding.length;i++){
            sb.append(embedding[i]);
            if(i < embedding.length-1){
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}