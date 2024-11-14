package com.enjoytrip.attraction.service.command;

import com.enjoytrip.attraction.controller.request.OpenAISearchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OpenAISearchCommand {
    String searchText;
    String selectedDate;
    String age;
    String gender;
    String mbti;

    public static OpenAISearchCommand from(OpenAISearchRequest request) {
        return new OpenAISearchCommand(
                request.getSearchText(),
                request.getSelectedDate(),
                request.getAge(),
                request.getGender(),
                request.getMbti()
        );
    }
}
