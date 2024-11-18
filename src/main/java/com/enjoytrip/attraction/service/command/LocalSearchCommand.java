package com.enjoytrip.attraction.service.command;

import com.enjoytrip.attraction.controller.request.LocalSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocalSearchCommand {
    Integer areaCode;
    Integer sigunguCode;
    Integer contentTypeId;
    String keyword;

    public static LocalSearchCommand from(LocalSearchRequest localSearchRequest) {
        return new LocalSearchCommand(
                localSearchRequest.getAreaCode(),
                localSearchRequest.getSigunguCode(),
                localSearchRequest.getContentTypeId(),
                localSearchRequest.getKeyword()
        );
    }
}
