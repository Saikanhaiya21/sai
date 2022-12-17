package com.fileUpload.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fileUpload.model.FileUpload;

public interface ImageService {

	public abstract void uploadFile(MultipartFile file) throws IOException;
	
	 public Optional<FileUpload> getImageByUuid(String uuid);
	 
	 public List<FileUpload> getAllImages();
}
