package com.autumnia.openai.basic.service;

import com.autumnia.openai.basic.controller.RequestMovie;
import com.autumnia.openai.basic.controller.RequestRcd;
import com.autumnia.openai.basic.domain.Answer;
import com.autumnia.openai.basic.domain.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatClient chatClient;


    public String getChat(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();  //  문자열만 가져온다.
    }

    public String get_chatmessage(String message) {
        return  Objects.requireNonNull(chatClient.prompt()
                        .system("당신은 교육자입니다. 개념을 간단하고 명료하게 설명하세요")
                        .user(message)
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput()
                .getText();  // getContent가 없어진 듯
    }

    public String chat_placeholder(RequestRcd request) {
        return  Objects.requireNonNull(chatClient.prompt()
            .system(sp -> sp
                    .param("subject", request.subject())
                    .param("tone", request.tone())
            )
            .user( request.message() )
            .call()
            .chatResponse())
            .getResult()
            .getOutput()
            .getText();  // getContent가 없어진 듯
    }

    public ChatResponse get_chatjson(RequestRcd request) {
        return chatClient.prompt()
            .system(sp -> sp
                    .param("subject", request.subject())
                    .param("tone", request.tone())
            )
            .user( request.message() )
            .call()
            .chatResponse();
    }

    public Answer get_chatdto(RequestRcd request) {
        return chatClient.prompt()
                .system(sp -> sp
                    .param("subject", request.subject())
                    .param("tone", request.tone())
                )
                .user( request.message() )
                .call()
                .entity(Answer.class);
    }

    public List<String> get_list(RequestRcd request) {
        return chatClient.prompt()
                .system(sp -> sp
                        .param("subject", request.subject())
                        .param("tone", request.tone())
                )
                .user( request.message() )
                .call()
                .entity(
                    new ListOutputConverter(
                        new DefaultConversionService()
                    )
                );
    }

    public Map<String, String> get_map(RequestRcd request) {
        return chatClient.prompt()
                .system(sp -> sp
                        .param("subject", request.subject())
                        .param("tone", request.tone())
                )
                .user( request.message() )
                .call()
                .entity( new ParameterizedTypeReference<Map<String, String>>(){} );
    }

    public List<Movie> get_object(RequestMovie request) {
        log.info( "subject: {}, tone: {}", request.subject(), request.tone());
        log.info( "director_name: {}, tone: {}", request.director_name(), request.message());


//        String template = """
//                Generate a list of movies directed by {director_name}.
//                if the director is unknown, rerurn null
//                한국 영화는 할글로 표기해줘
//                Each moive should include a tile and release year. {format}
//                """;

        List<Movie> movies = chatClient.prompt()
                .system(
    sp -> sp.param("subject", request.subject())
                            .param("tone", request.tone())
                )
                .user(
                        sp -> sp.text(request.message())
                                .param("director_name", request.director_name())
                                .param("format", "json")
                )
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>(){});


        for( Movie movie: movies) {
            log.info( "movie: {}", movies.toString() );
        }

        return movies;

    }

    public String get_response(String message) {

        return chatClient.prompt()
                .user( message)
                .call()
                .content();
    }

    public void start_chat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Enter your message");
        while( true ) {
            String msg = scanner.nextLine();
            if ( msg.equals("exit") ) {
                System.out.println();
                break;
            }
            var response = get_response(msg) ;
            log.info("My bot: {}", response);
        }
        scanner.close();
    }
}
