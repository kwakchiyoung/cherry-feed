package com.bazzi.cherryfeed.apps.file.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "파일업로드응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadResponseDto {
    private String mesage;
    private Long fileId;
}
