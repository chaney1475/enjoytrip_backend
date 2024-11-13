package com.enjoytrip.attraction.controller.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class OpenAISearchRequest {
    String searchText;
    String selectedDate;
    String age;
    String gender;
    String mbti;
}
