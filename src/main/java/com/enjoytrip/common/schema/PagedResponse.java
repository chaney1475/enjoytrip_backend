package com.enjoytrip.common.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagedResponse<T> {
    private List<T> content;      // 현재 페이지의 데이터
    private int totalPages;       // 전체 페이지 수
    private long totalElements;   // 전체 데이터 개수
    private int currentPage;      // 현재 페이지 번호
    private int size;             // 페이지당 데이터 수
}

