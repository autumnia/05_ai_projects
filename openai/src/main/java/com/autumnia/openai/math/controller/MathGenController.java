package com.autumnia.openai.math.controller;

import com.autumnia.openai.math.domain.MathAnalysisRcd;
import com.autumnia.openai.math.service.MathGenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequestMapping("/ai")
@RestController
@RequiredArgsConstructor
public class MathGenController {
    @Value("${upload.path}")
    private String uploadPath;

    private final MathGenService mathGenService;

    @PostMapping("/math-analyze")
    public ResponseEntity<MathAnalysisRcd> getMultimodalResponse(
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam(defaultValue = "이 이미지에 무엇이 있나요?") String message)
            throws IOException
    {
        // Ensure the upload directory exists
        check_upload_directory();

        // Save the uploaded file to the specified upload path
        String filename = save_uploaded_file(imageFile);
        String image_url = "/ai/uploads/" + filename;
//        log.info("image_url: {}", image_url);

        // Analyze the image
        String analysis_text = mathGenService.analyze_image(imageFile, message);
//        log.info("analysis_text: {}", analysis_text);

        // 세제곱근, 제곱근, 곱셈
        String search_keyword = mathGenService.extractKeyYouTubeSearch(analysis_text);
//        log.info("search_keyword: {}", search_keyword);

        List<String> youtube_urls = mathGenService.searchYouTubeVideos(search_keyword);
//        log.info("youtube_urls: {}", youtube_urls);

        MathAnalysisRcd response = new MathAnalysisRcd(image_url, analysis_text, youtube_urls);
        return ResponseEntity.ok(response);
    }

    private String save_uploaded_file(MultipartFile imageFile) throws IOException {
        String filename = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadPath, filename);
        Files.write(filePath, imageFile.getBytes());
        return filename;
    }

    private void check_upload_directory() {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }
}
