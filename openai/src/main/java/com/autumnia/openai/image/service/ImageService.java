package com.autumnia.openai.image.service;

import com.autumnia.openai.image.domain.ImageRequestRcd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final OpenAiImageModel openAiImageModel;

    public ImageResponse get_image_gen(ImageRequestRcd request){
        // 모델설정
        // 이미지 품질
        // 생성할 이미지 개수
        return openAiImageModel
                .call(new ImagePrompt(request.message(),
                        OpenAiImageOptions.builder()
                                .withModel(request.model()) // 모델설정
                                .withQuality("hd") // 이미지 품질
                                .withN(request.n()) // 생성할 이미지 개수
                                .withHeight(1024)
                                .withWidth(1024)
                                .build())
                );
    }
}
