package com.autumnia.openai.gtpsql.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.List;

@Slf4j
public record SqlResponse(
    String sqlQuery,
    List<Map<String, Object>> results
) {
}
