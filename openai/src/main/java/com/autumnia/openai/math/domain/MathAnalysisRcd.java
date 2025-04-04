package com.autumnia.openai.math.domain;

import java.util.List;

public record MathAnalysisRcd(
        String image_url,
        String analysis_text,
        List<String> youtube_urls
) {
}
