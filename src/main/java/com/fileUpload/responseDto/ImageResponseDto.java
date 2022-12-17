package com.fileUpload.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {

	private String id;
    private String name;
    private Long size;
    private String url;
    private String contentType;
}
