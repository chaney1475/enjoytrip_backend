package com.enjoytrip.image.service;

import com.enjoytrip.common.exception.ExceptionCode;
import com.enjoytrip.image.domain.Image;
import com.enjoytrip.image.domain.ImageFile;
import com.enjoytrip.image.exception.ImageException;
import com.enjoytrip.image.mapper.ImageMapper;
import com.enjoytrip.image.service.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageUploader imageUploader;
    private final ImageMapper imageRepository;

    public ImageDto save(final MultipartFile image) {
        if (image.isEmpty()) {
            throw new ImageException(ExceptionCode.NULL_IMAGE);
        }

        // 이미지 파일 저장
        ImageFile imageFile = new ImageFile(image);
        String imageUrl = imageUploader.uploadImage(imageFile);

        // 이미지 엔티티 저장 및 id 자동 생성
        Image newImage = new Image(imageUrl);
        imageRepository.save(newImage);

        // DTO 변환
        return ImageDto.from(newImage);
    }

}
