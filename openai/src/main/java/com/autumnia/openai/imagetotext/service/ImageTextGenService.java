package com.autumnia.openai.imagetotext.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImageTextGenService {
    @Value("classpath:/system.message")
    private Resource defaultSystemMessage;

    private final ChatModel chatModel;


    public String analyze_image(MultipartFile imageFile, String message) {
        // MIME 타입 결정
        String contentType = imageFile.getContentType();
        if (!MimeTypeUtils.IMAGE_PNG_VALUE.equals(contentType) &&
                !MimeTypeUtils.IMAGE_JPEG_VALUE.equals(contentType)) {
            throw new IllegalArgumentException("지원되지 않는 이미지 형식입니다.");
        }
        try {
            // Media 객체 생성
            var media = new Media(MimeType.valueOf(contentType), imageFile.getResource());
            // 사용자 메시지 생성
            var userMessage = new UserMessage(message, media);
            // 시스템 메시지 생성
            var systemMessage = new SystemMessage(defaultSystemMessage);
            // AI 모델 호출
            return chatModel.call(userMessage, systemMessage);
        } catch (Exception e) {
            throw new RuntimeException("이미지 처리 중 오류가 발생했습니다.", e);
        }
    }
}
