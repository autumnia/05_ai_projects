package com.autumnia.rag.pdfrag.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataLoader {
    private final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    // # 0. PDF 경로(resources 아래)
    @Value("classpath:/SPRi AI Brief_4월호_산업동향_250407_F.pdf")
    private Resource pdfResource;

    @PostConstruct
    public void init(){
        Integer count=jdbcClient.sql("select count(*) from vector_store")
                .query(Integer.class)
                .single();
        log.info("테이블에 있는 레코드 수: {}", count);

        if ( count != 0 ) {
            log.info( "DB에 이미 적재 된 경우 다시 로딩하지 않는다.");
            return;
        }

//        if(count==0){
            log.info("PDF 파일 적재 중.....");

            // PDF Reader
            PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
                    .withPageTopMargin(0)
                    .withPageExtractedTextFormatter(
                        ExtractedTextFormatter.builder()
                        .withNumberOfTopTextLinesToDelete(0)
                        .build()
                    )
                    .withPagesPerDocument(1)
                    .build();

            // # 1.단계 : 문서로드(Load Documents)
            List<Document> documents = load_document(config);

            // # 2.단계 : 문서분할(Split Documents)
            List<Document> splitDocuments = split_documents(documents);

            // # 3.단계 : 임베딩(Embedding) -> 4.단계 : DB에 저장(백터스토어 생성)
            save_to_db(splitDocuments);
//        }
    }

    private void save_to_db(List<Document> splitDocuments) {
        vectorStore.accept(splitDocuments); // OpenAI 임베딩을 거친다.
        log.info("Application is ready to Serve the Requests");
    }

    private static List<Document> split_documents(List<Document> documents) {
        TokenTextSplitter splitter = new TokenTextSplitter(1000, 400, 10, 5000, true);
        List<Document> splitDocuments=splitter.apply(documents);
        System.out.println(splitDocuments.size()); // 45
        System.out.println(splitDocuments.get(0)); // 25

        return splitDocuments;
    }

    private List<Document> load_document(PdfDocumentReaderConfig config) {
        PagePdfDocumentReader pdfReader =new PagePdfDocumentReader(pdfResource, config);
        List<Document> documents=pdfReader.get();
        // System.out.println(documents.toString()); // 25
        // 1000글자 단위로 자른다.

        return documents;
    }
}
