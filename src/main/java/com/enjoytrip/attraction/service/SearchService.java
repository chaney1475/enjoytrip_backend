package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.service.command.LocalSearchCommand;
import com.enjoytrip.attraction.service.command.OpenAISearchCommand;
import com.enjoytrip.attraction.service.dto.*;
import com.enjoytrip.attraction.util.ParserJson;
import com.enjoytrip.attraction.util.PromptTemplateLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${API_KEY.DATA}")
    private String API_KEY_DATA;

    private final WebClient webClient;
    private final ChatModel chatModel;
    private final PromptTemplateLoader promptLoader;

    public Mono<List<AreaDto>> getArea() {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/B551011/KorService1/areaCode1")
                        .queryParam("serviceKey", API_KEY_DATA)
                        .queryParam("numOfRows", "17")
                        .queryParam("pageNo", "1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "AppTest")
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(ParserJson::parseAreaCodeDto);
    }

    public Mono<List<SigunguDto>> getSigungu(String areaCode) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/B551011/KorService1/areaCode1")
                        .queryParam("serviceKey", API_KEY_DATA)
                        .queryParam("numOfRows", "100")
                        .queryParam("pageNo", "1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "AppTest")
                        .queryParam("areaCode", areaCode)
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(ParserJson::parseSigunguDto);
    }

    public Mono<List<LocalSearchDto>> getLocalSearch(LocalSearchCommand localSearchCommand) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/B551011/KorService1/areaBasedList1")
                        .queryParam("serviceKey", API_KEY_DATA)
                        .queryParam("numOfRows", "100")
                        .queryParam("pageNo", "1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "AppTest")
                        .queryParam("listYN", "Y")
                        .queryParam("arrange", "A")
                        .queryParam("contentTypeId", localSearchCommand.getContentTypeId())
                        .queryParam("areaCode", localSearchCommand.getAreaCode())
                        .queryParam("sigunguCode", localSearchCommand.getSigunguCode())
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(json -> ParserJson.parseLocalSearchDto(json, localSearchCommand.getKeyword()));
    }

    public List<OpenAISearchDto> OpenAiSearch(OpenAISearchCommand openAISearchCommand) {
        // 유저 프롬프트 템플릿 로드 및 변수 설정
        String userPromptTemplate = promptLoader.loadUserPrompt();
        PromptTemplate userTemplate = new PromptTemplate(userPromptTemplate);
        userTemplate.add("date", openAISearchCommand.getSelectedDate());
        userTemplate.add("age", openAISearchCommand.getAge());
        userTemplate.add("gender", openAISearchCommand.getGender());
        userTemplate.add("mbti", openAISearchCommand.getMbti());
        userTemplate.add("text", openAISearchCommand.getSearchText());
        String userCommand = userTemplate.render();

        // 시스템 프롬프트 템플릿 로드
        String systemPromptTemplate = promptLoader.loadSystemPrompt();
        PromptTemplate systemTemplate = new PromptTemplate(systemPromptTemplate);
        String systemCommand = systemTemplate.render();

        // 메시지 생성
        Message userMessage = new UserMessage(userCommand);
        Message systemMessage = new SystemMessage(systemCommand);

        // API 호출 및 JSON 응답 받기
        String response = chatModel.call(userMessage, systemMessage);

        // JSON 응답을 AttractionDto 리스트로 변환
        return ParserJson.parseOpenAISearchDto(response);
    }
}
