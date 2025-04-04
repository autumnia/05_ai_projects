package com.autumnia.openai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String upload_path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ai/uploads/**")  // URL 패턴
                .addResourceLocations("file:" + upload_path);  // 파일 시스템의 실제 경로
        // src/main/resources/static/uploads/
    }

}
