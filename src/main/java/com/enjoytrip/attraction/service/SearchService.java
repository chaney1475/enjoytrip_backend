package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.exception.AttractionException;
import com.enjoytrip.attraction.service.command.OpenAISearchCommand;
import com.enjoytrip.attraction.service.dto.OpenAISearchDto;
import com.enjoytrip.attraction.util.PromptTemplateLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {

    private final ChatModel chatModel;
    private final PromptTemplateLoader promptLoader;
    private final ObjectMapper objectMapper;

    public List<OpenAISearchDto> OpenAiSearchService(OpenAISearchCommand openAISearchCommand) {
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
        return parseOpenAISearchDto(response);
    }

    private List<OpenAISearchDto> parseOpenAISearchDto(String response) {
        List<OpenAISearchDto> attractions = new ArrayList<>();
        JsonNode root = null;

        try {
            root = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new AttractionException("OPENAI_READ_FAILED", "openAI 검색 결과 읽기 실패", "openAI 검색 결과 읽기 실패에 실패하였습니다.");
        }

        JsonNode attractionsNode = root.path("attractions");

        for (JsonNode node : attractionsNode) {
            OpenAISearchDto openAISearchDto = new OpenAISearchDto();
            openAISearchDto.setAddress(node.path("address").asText());
            openAISearchDto.setFirstImage(node.path("firstImage").asText());
            openAISearchDto.setMapX(new BigDecimal(node.path("mapX").asText()));
            openAISearchDto.setMapY(new BigDecimal(node.path("mapY").asText()));
            openAISearchDto.setTel(node.path("tel").asText());
            openAISearchDto.setTitle(node.path("title").asText());
            openAISearchDto.setSigunguName(node.path("sigunguName").asText());
            openAISearchDto.setReason(node.path("reason").asText()); // reason 필드 설정
            attractions.add(openAISearchDto);
        }

        return attractions;
    }
}
