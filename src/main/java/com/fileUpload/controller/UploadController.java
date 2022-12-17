package com.fileUpload.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileUpload.model.FileUpload;
import com.fileUpload.responseDto.ImageResponseDto;
import com.fileUpload.service.ImageService;



@RestController
@CrossOrigin("*")
public class UploadController {

	@Autowired(required = false)
	private ImageService imageService;
	
	@RequestMapping(value = "/uploadImageApi", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
        	imageService.uploadFile(file);

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }
	
	@RequestMapping(value = "/getImageListApi", method = RequestMethod.GET)
	public List<ImageResponseDto> getAllImage() {
        return imageService.getAllImages()
                          .stream()
                          .map(this::mapToImageResponseDto)
                          .collect(Collectors.toList());
    }

    private ImageResponseDto mapToImageResponseDto(FileUpload image) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                        .path("/files/")
                                                        .path(image.getId())
                                                        .toUriString();
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setId(image.getId());
        imageResponseDto.setName(image.getName());
        imageResponseDto.setContentType(image.getContentType());
        imageResponseDto.setSize(image.getSize());
        imageResponseDto.setUrl(downloadURL);

        return imageResponseDto;
    }
	
    
	@RequestMapping(value = "/getImageBYUuidApi/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<FileUpload> fileEntityOptional = imageService.getImageByUuid(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        FileUpload fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                             .contentType(MediaType.valueOf(fileEntity.getContentType()))
                             .body(fileEntity.getData());
    }
	 
}
