package com.aicopilot.rag;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.Loader;

import java.io.InputStream;

@Service
public class PdfService {

    public String extractText(InputStream inputStream) {

        try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read PDF", e);
        }
    }
}