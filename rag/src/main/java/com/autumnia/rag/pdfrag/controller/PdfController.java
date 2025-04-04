package com.autumnia.rag.pdfrag.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class PdfController {
    private final ChatModel chatModel; // OpenAI
    private final VectorStore vectorStore;

    // # 6.단계 : 프롬프트 생성(Create Prompt)
    private String prompt = """
            You are an assistant for question-answering tasks.
            Use the following pieces of retrieved context to answer the question.
            If you don't know the answer, just say that you don.t know.
            Answer in Korean.  
            
            #Question:
            {input}               
            
            #Context :
            {documents}
                                         
            #Answer:                                    
            """;
    /*
            질문에 답변하는 것입니다. 질문에 정확하게 답변하기 위해 문서에 있는 정보를 사용해야 합니다.
            만약 정보가 부족하거나 문서에서 답을 찾을 수 없다면, 알지 못한다고 간단히 답변하세요.
            답변은 한글로 해줘
     */


    @GetMapping("/api/answer")
    public String simplify(String question) {

        PromptTemplate template = new PromptTemplate(prompt);
        Map<String, Object> promptsParameters = new HashMap<>();

        promptsParameters.put("input", question);
        promptsParameters.put("documents", findSimilarData(question));

        return get_result_from_chatmodel(template, promptsParameters);
    }

    private String get_result_from_chatmodel(PromptTemplate template, Map<String, Object> promptsParameters) {
        return chatModel
                .call(template.create(promptsParameters))
                .getResult()
                .getOutput()
                .getText();
    }

    // # 5.단계 - 검색기(Retriever) 생성---|(Question)<---유사도 검색(similarity)
    // 문서에 포홤되어 있는 정보를 검색하고 생성
    private String findSimilarData(String question) {
        List<Document> documents = vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(question)
                .topK(2)
                .build()
        );

        System.out.println(documents.toString());
        return documents
                .stream()
                .map(document -> document.getText().toString())
                .collect(Collectors.joining());
    }
}
