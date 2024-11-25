package com.enjoytrip.user.service;

import com.enjoytrip.common.exception.ExceptionCode;
import com.enjoytrip.common.exception.NotFoundException;
import com.enjoytrip.image.domain.Image;
import com.enjoytrip.image.mapper.ImageMapper;
import com.enjoytrip.jwt.exception.TokenException;
import com.enjoytrip.social.domain.SocialType;
import com.enjoytrip.user.domain.User;
import com.enjoytrip.user.mapper.UserMapper;

import com.enjoytrip.user.service.command.RegisterCommand;
import com.enjoytrip.user.service.command.UserUpdateCommand;
import com.enjoytrip.user.service.dto.UserCommand;
import com.enjoytrip.user.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.enjoytrip.common.exception.ExceptionCode.NOT_FOUND_USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;  // MyBatis 매퍼 사용
    private final ImageMapper imageRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserDTO createSocialUser(UserCommand userCommand) {
        String socialId = userCommand.getSocialId();
        Optional<User> existingUser = userMapper.findBySocialId(socialId);  // User로 변경

        // 존재하는 회원일 경우
        if (existingUser.isPresent()) {
            return UserDTO.from(existingUser.get());
        }
        log.error("existing user id:{}, user {}", socialId, existingUser);
        // 신규 DB에 저장
        User newUser = User.builder()
                .email(userCommand.getEmail())
                .nickname(userCommand.getName())
                .socialType(SocialType.valueOf(userCommand.getSocialType()))
                .socialId(userCommand.getSocialId())
                .avatarUrl(null)  // 소셜 로그인 회원 가입 시 프로필 사진 추후 등록
                .build();

        userMapper.insertUser(newUser);  // userMapper로 변경

        return UserDTO.from(newUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        // 사용자 존재 여부 확인
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new NotFoundException("회원 탈퇴 실패", "해당 유저가 존재하지 않습니다."));

        // 사용자 삭제
        userMapper.deleteUser(userId);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new NotFoundException("사용자 정보 조회 실패", "해당 유저가 존재하지 않습니다."));
        return UserDTO.from(user);
    }

    @Transactional
    public UserDTO createEmailUser(RegisterCommand registerCommand) {
        // 이메일 중복 확인
        Optional<User> existingUser = userMapper.findByEmail(registerCommand.getEmail());
        if (existingUser.isPresent()) {
            throw new TokenException(ExceptionCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(registerCommand.getPassword());

        // 새로운 사용자 생성
        User newUser = User.builder()
                .email(registerCommand.getEmail())
                .password(encryptedPassword)
                .nickname(registerCommand.getNickname())
                .socialType(SocialType.NONE)
                .socialId(null)
                .avatarUrl(null)
                .build();

        // 사용자 저장
        userMapper.insertUser(newUser);

        // 저장한 사용자 조회 (ID 포함된 상태)
        User savedUser = userMapper.findByEmail(registerCommand.getEmail())
                .orElseThrow(() -> new NotFoundException("회원가입 실패", "사용자를 찾을 수 없습니다."));

        // AuthClaims 반환
        return UserDTO.from(savedUser);
    }

    @Transactional
    public UserDTO updateUser(UserUpdateCommand command) {
        User user = userMapper.findById(command.getUserId())
                .orElseThrow(() -> new NotFoundException("사용자 정보 수정 실패", "해당 유저가 존재하지 않습니다."));

        Image image = imageRepository.findById(command.getAvatarUrlId());

        user.update(command.getNickname(), image.getUrl());
        userMapper.updateUser(user);

        return UserDTO.from(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        User user = userMapper.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        return UserDTO.from(user);
    }
}
