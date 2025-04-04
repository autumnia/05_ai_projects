package com.autumnia.openai.speechtoimage.service;

import com.autumnia.openai.speechtoimage.domain.ImageRcd;
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
public class STImageService {
    private final OpenAiImageModel openAiImageModel;

    public ImageResponse getImageGen(ImageRcd request){
        ImageResponse imageResponse=openAiImageModel
                .call(new ImagePrompt(request.message(),
                        OpenAiImageOptions.builder()
                                .withModel(request.model()) // 모델설정
                                .withQuality("hd") // 이미지 품질
                                .withN(request.n()) // 생성할 이미지 개수
                                .withHeight(1024)
                                .withWidth(1024).build())
                );
        return  imageResponse;
    }
}
