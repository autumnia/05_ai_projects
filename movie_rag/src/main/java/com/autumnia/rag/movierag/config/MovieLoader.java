package com.example.pgvector.config;

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
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MovieLoader {
    private  final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    @Value("classpath:movie_plots_korean.txt")
    Resource resource;

    @PostConstruct
    public void init() throws Exception {
        Integer count=jdbcClient.sql("select count(*) from movie_vector")
                .query(Integer.class)
                .single();
        log.info("테이블에 있는 레코드 수: {}", count);

        if ( count != 0 ) {
            log.info( "DB에 이미 적재 된 경우 다시 로딩하지 않는다.");
            return;
        }

        if(count==0){
            List<Document> documents = Files.lines(resource.getFile().toPath())
                    .map(Document::new)
                    .toList();
//                    .collect(Collectors.toList());


            TextSplitter textSplitter = new TokenTextSplitter();
            for(Document document : documents) {
                List<Document> splitteddocs = textSplitter.split(document);
                System.out.println("before adding document: " + document.getText());
                vectorStore.add(splitteddocs); //임베딩
                System.out.println("Added document: " + document.getText());
                Thread.sleep(1000); // 1초
            }
            System.out.println("Application is ready to Serve the Requests");
        }
    }
}
/*
 Spring AI  VS LangChain
 */