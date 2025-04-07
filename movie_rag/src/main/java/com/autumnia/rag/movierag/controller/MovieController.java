package com.autumnia.rag.movierag.controller;

import com.autumnia.rag.movierag.util.URLTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/ai")
public class MovieController {
    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    @GetMapping("/movie")
    public String getRecommendationForm() {
        return "movie_rag";  // returns the HTML file 'recommend.html'
    }

    // 어떤 남자가 억울하게 감옥에 갇혔고, 그는 탈출을 계획합니다 - query ->임베딩[   ,,,,,,,   ]
    @PostMapping("/movie/recommend")
    public String recommendMovies1(@RequestParam("query") String query, Model model) throws Exception {
        // Fetch similar movies using vector store
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .similarityThreshold(0.85)
                        .topK(1)
                        .build()
        );

        if (!results.isEmpty()) {
            Document topResult = results.get(0);
            String movieContent = topResult.getText();
            String title=movieContent.substring(movieContent.indexOf("(")+1, movieContent.lastIndexOf(")")); //(쇼생크탈출)
            // Use Jsoup to fetch the YouTube URL
            List<String> url = URLTest.searchYouTube(title);
            model.addAttribute("title", title);
            // Add the movie details and YouTube URL to the model
            model.addAttribute("results", movieContent);
            model.addAttribute("youtubeUrls", url);
        }
        else{
            model.addAttribute("message", "No closely related movies found.");
        }

        model.addAttribute("query", query);

        return "movieRAG";  // Renders the 'movieRAG.html' view
    }
}