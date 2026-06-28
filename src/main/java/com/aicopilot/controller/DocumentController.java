package com.aicopilot.controller;

import com.aicopilot.rag.DocumentIngestionService;
import com.aicopilot.rag.PdfService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/copilot")
public class DocumentController {

    private final DocumentIngestionService ingestionService;
    private  final PdfService pdfService;
    public DocumentController(DocumentIngestionService ingestionService,PdfService pdfService) {
        this.ingestionService = ingestionService;
        this.pdfService=pdfService;
    }

    @PostMapping("/ingest")
    public String ingest(@RequestBody String document) {

        ingestionService.ingest(document);

        return "Document ingested successfully";
    }

    @PostMapping("/uploadPdf")
    public String uploadPdf(@RequestParam MultipartFile file) throws IOException {

        String text = pdfService.extractText(file.getInputStream());

        ingestionService.ingest(text);

        return "PDF uploaded successfully";
    }
}