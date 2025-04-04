package com.autumnia.openai.imagetotext.controller;

import com.autumnia.openai.imagetotext.domain.ImageAnalysisRcd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autumnia.openai.imagetotext.service.ImageTextGenService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;

@Slf4j
@Controller
@RequestMapping("/ai")
@RequiredArgsConstructor
public class ImageTextGenController {
    @Value("${upload.path}")
    private String uploadPath;

    private final ImageTextGenService imageTextGenService;

    @GetMapping("/image-to-text")
    public String image_to_view(){
        return "image_to_text"; // imageview.html
    }

    @PostMapping("/image-to-text/analyze")
    public ResponseEntity<ImageAnalysisRcd> getMultimodalResponse(
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam(defaultValue = "이 이미지에 무엇이 있나요?") String message)
            throws IOException {

        // Ensure the upload directory exists
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs(); // uploads
        }

        // Save the uploaded file to the specified upload path
        String filename = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadPath, filename);
        Files.write(filePath, imageFile.getBytes()); // 업로드

        // Analyze the image
        String analysisText = imageTextGenService.analyze_image(imageFile, message);
        // http://localhost:8080/ai/uploads/323232323.png
        String imageUrl = "/ai/uploads/" + filename;

        ImageAnalysisRcd response = new ImageAnalysisRcd(imageUrl, analysisText, null);
        return ResponseEntity.ok(response);  //  { "imageUrl":".....", "analysisText":".........." }
    }
}
