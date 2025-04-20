package com.autumnia.hotel_rag.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.nio.file.Files;
import java.util.List;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class HotelLoader {
    private  final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    @Value("classpath:data.txt")
    Resource resource;

    @PostConstruct
    public void init() throws Exception {
        Integer count=jdbcClient.sql("select count(*) from hotel_vector")
                .query(Integer.class)
                .single();
        log.info("No of Records in the PG Vector Store={}", count);

        if ( count != 0 ) {
            log.info( "DB에 이미 적재 된 경우 다시 로딩하지 않는다.");
            return;
        }

        List<Document> documents = Files.lines(resource.getFile().toPath())
                .map(Document::new)
                .toList();

        TextSplitter textSplitter = new TokenTextSplitter();
        for(Document document : documents) {
            List<Document> splitteddocs = textSplitter.split(document);
            log.info("before adding document: {}", document.getText());
            vectorStore.add(splitteddocs); //임베딩
            log.info("Added document: {}", document.getText());
            Thread.sleep(1000); // 1초
        }
        log.info("Application is ready to Serve the Requests");
    }
}