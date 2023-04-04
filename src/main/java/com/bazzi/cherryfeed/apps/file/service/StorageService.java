package com.bazzi.cherryfeed.apps.file.service;

import com.bazzi.cherryfeed.apps.file.domain.FileStore;
import com.bazzi.cherryfeed.apps.file.dto.FileUploadResponseDto;
import com.bazzi.cherryfeed.apps.file.repository.FileDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final FileDataRepository fileDataRepository;
    private static final String FOLDER_PATH = "C:\\cherry-feed-server\\src\\main\\resources\\files\\";

    public FileUploadResponseDto uploadImageToFileSystem(MultipartFile file) throws IOException {
        log.info("upload file: {}", file.getOriginalFilename());
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileStore fildstore = FileStore.builder()
                .fileName(file.getOriginalFilename())
                .type(file.getContentType())
                .saveLocation(filePath)
                .build();
        FileStore fileStore = fileDataRepository.save(fildstore);

        // 파일 결로
        file.transferTo(new File(filePath));

        if (fileStore != null) {
            FileUploadResponseDto dto = FileUploadResponseDto.builder()
                    .mesage("file uploaded successfully! filePath : " + filePath)
                    .fileId(fildstore.getId())
                    .build();
            return dto;
        }

        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileStore fileStore = fileDataRepository.findByFileName(fileName)
                .orElseThrow(RuntimeException::new);

        String filePath = fileStore.getSaveLocation();

        log.info("download fileData: {}", fileStore);
        log.info("download filePath: {}", filePath);

        return Files.readAllBytes(new File(filePath).toPath());
    }
}
