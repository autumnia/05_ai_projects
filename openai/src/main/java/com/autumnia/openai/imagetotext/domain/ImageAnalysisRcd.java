package com.autumnia.openai.imagetotext.domain;

import java.util.List;

public record ImageAnalysisRcd(
        String image_url,
        String analysis_text,
        List<String> youtube_urls
) {
}


