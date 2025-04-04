package com.autumnia.openai.speechtoimage.controller;

import com.autumnia.openai.speechtoimage.domain.ImageRcd;
import com.autumnia.openai.speechtoimage.service.STImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class STImageController {
    private final STImageService imageService;

    @PostMapping(value = "/speech-to-image", consumes = "application/json; charset=UTF-8")
    public List<String> imageGenController(@RequestBody ImageRcd request) throws IOException {
        //String message = request.get("message"); // Map에서 "message" 키의 값을 가져옴
        ImageResponse imageResponse=imageService.getImageGen(request);
        //String imageUrl= imageResponse.getResult().getOutput().getUrl();
        //response.sendRedirect(imageUrl);
        List<String> imageUrls= imageResponse.getResults()
                .stream()
                .map(image_url->image_url.getOutput().getUrl())
                .toList();

        return imageUrls;
    }
}
