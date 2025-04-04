package com.autumnia.openai.image.controller;

import com.autumnia.openai.image.domain.ImageRequestRcd;
import com.autumnia.openai.image.service.ImageService;
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
@RequiredArgsConstructor
@RequestMapping("/ai")
public class ImageGenerationController {
    private final ImageService imageService;

    @PostMapping(value = "/image", consumes = "application/json; charset=UTF-8")
    public List<String> image(@RequestBody ImageRequestRcd request) throws IOException {
        //String message = request.get("message"); // Map에서 "message" 키의 값을 가져옴
        ImageResponse imageResponse = imageService.get_image_gen(request);
        //String imageUrl= imageResponse.getResult().getOutput().getUrl();
        //response.sendRedirect(imageUrl);
        List<String> imageUrls = imageResponse.getResults()
                .stream()
                .map(result->result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }
}
