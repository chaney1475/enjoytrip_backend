package com.enjoytrip.attraction.util;

import com.enjoytrip.attraction.exception.AttractionException;
import com.enjoytrip.attraction.service.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParserJson {

    private static final ObjectMapper staticObjectMapper = new ObjectMapper();

    static public List<OpenAISearchDto> parseOpenAISearchDto(String response) {
        List<OpenAISearchDto> attractions = new ArrayList<>();
        JsonNode root = null;

        try {
            root = staticObjectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new AttractionException("OPENAI_READ_FAILED", "openAI 검색 결과 읽기 실패", "openAI 검색 결과 읽기에 실패하였습니다.");
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
            openAISearchDto.setReason(node.path("reason").asText());
            attractions.add(openAISearchDto);
        }

        return attractions;
    }

    public static Mono<List<AreaDto>> parseAreaCodeDto(String response) {
        List<AreaDto> areas = new ArrayList<>();
        JsonNode rootNode = null;

        try {
            rootNode = staticObjectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new AttractionException("AREA_READ_FAILED", "Area 정보 반환 결과 읽기 실패", "Area 반환 결과 읽기에 실패하였습니다.");
        }

        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                String code = itemNode.path("code").asText();
                String name = itemNode.path("name").asText();
                areas.add(new AreaDto(code, name));
            }
            return Mono.just(areas);
        }

        return Mono.empty();
    }

    public static Mono<List<SigunguDto>> parseSigunguDto(String response) {
        List<SigunguDto> sigungus = new ArrayList<>();
        JsonNode rootNode = null;

        try {
            rootNode = staticObjectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new AttractionException("SIGUNGU_READ_FAILED", "Sigungu 정보 반환 결과 읽기 실패", "Sigungu 반환 결과 읽기에 실패하였습니다.");
        }

        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                String code = itemNode.path("code").asText();
                String name = itemNode.path("name").asText();
                sigungus.add(new SigunguDto(code, name));
            }
            return Mono.just(sigungus);
        }

        return Mono.empty();
    }

    public static Mono<List<LocalSearchDto>> parseLocalSearchDto(String response, String keyword) {
        List<LocalSearchDto> localSearchDtos = new ArrayList<>();
        JsonNode rootNode = null;

        try {
            rootNode = staticObjectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new AttractionException("LOCAL_SEARCH_READ_FAILED", "지역 기반 검색 정보 반환 결과 읽기 실패", "지역 기반 검색 반환 결과 읽기에 실패하였습니다.");
        }

        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                if (keyword != null && !keyword.trim().isEmpty() && !itemNode.path("title").asText().contains(keyword))
                    continue;

                LocalSearchDto localSearchDto = new LocalSearchDto();
                localSearchDto.setAddress(itemNode.has("addr1") ? itemNode.get("addr1").asText() : "");
                localSearchDto.setContentId(itemNode.has("contentid") ? itemNode.get("contentid").asText() : "");
                localSearchDto.setFirstImage(itemNode.has("firstimage") ? itemNode.get("firstimage").asText() : "");
                localSearchDto.setMapX(itemNode.has("mapx") ? new BigDecimal(itemNode.get("mapx").asText()) : null);
                localSearchDto.setMapY(itemNode.has("mapy") ? new BigDecimal(itemNode.get("mapy").asText()) : null);
                localSearchDto.setTel(itemNode.has("tel") ? itemNode.get("tel").asText() : "");
                localSearchDto.setTitle(itemNode.has("title") ? itemNode.get("title").asText() : "");
                localSearchDto.setAreaCode(itemNode.has("areacode") ? itemNode.get("areacode").asText() : "");
                localSearchDto.setSigunguCode(itemNode.has("sigungucode") ? itemNode.get("sigungucode").asText() : "");
                localSearchDtos.add(localSearchDto);
            }

            return Mono.just(localSearchDtos);
        }

        return Mono.empty();
    }
}
