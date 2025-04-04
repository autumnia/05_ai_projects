package com.autumnia.openai.gtpsql.controller;

import com.autumnia.openai.gtpsql.domain.SqlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class SearchQuery {
    @Value("classpath:/movies.sql")
    private Resource ddl_resource;

    @Value("classpath:/sql-prompt-template.st")
    private Resource sql_prompt_template_resource;

    private final ChatClient aiClient;
    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/sql")
    public SqlResponse sql(@RequestParam(name = "question") String question) throws IOException {
        String ddl = ddl_resource.getContentAsString(Charset.defaultCharset()); // UTF-8
        // LLM 자동으로 select SQL이 생성
        String query = aiClient.prompt()
                .user(userSpec -> userSpec
                        .text(sql_prompt_template_resource)
                        .param("question", question)
                        .param("ddl", ddl)
                )
                .call()
                .content();

        if (query.toLowerCase().startsWith("select")) {
            return new SqlResponse(query, jdbcTemplate.queryForList(query));
        }

        return new SqlResponse(query, List.of()); // null
    }

}
