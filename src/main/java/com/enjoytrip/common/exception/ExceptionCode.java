package com.enjoytrip.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    NOT_FOUND_IMAGE_ID("NOT_FOUND", null, "요청하신 이미지는 존재하지 않습니다."),
    TOKEN_INVALID("TOKEN_ERROR", "로그인 실패", "로그인에 실패했습니다. 다시 로그인 해주세요."),
    DUPLICATE_EMAIL("USER_ERROR", "이메일 중복", "이미 존재하는 이메일입니다. 다른 이메일을 사용해주세요."),
    LOGIN_REQUIRED("LOGIN_REQUIRED", "로그인 필요", "이 기능을 이용하려면 로그인이 필요합니다."),

    NOT_FOUND_USER("USER_NOT_FOUND", "사용자 조회 실패", "유저 조회에 실패 했습니다."),
    INVALID_TOKEN_PREFIX("INVALID_TOKEN_PREFIX", "인증 실패", "유효하지 않은 토큰입니다."),
    NULL_IMAGE("IMAGE_ERROR", null, "업로드한 이미지 파일이 NULL입니다."),
    INVALID_IMAGE_URL("IMAGE_ERROR", null, "요청한 이미지 URL의 형식이 잘못되었습니다."),
    INVALID_IMAGE_PATH("IMAGE_ERROR", null, "이미지를 저장할 경로가 올바르지 않습니다."),
    FAIL_IMAGE_NAME_HASH("IMAGE_ERROR", null, "이미지 이름을 해싱하는 데 실패했습니다."),
    INVALID_IMAGE("IMAGE_ERROR", null, "올바르지 않은 이미지 파일입니다.");

    private final String code;
    private final String title;
    private final String message;
}
