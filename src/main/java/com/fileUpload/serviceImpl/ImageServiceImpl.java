package com.fileUpload.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileUpload.model.FileUpload;
import com.fileUpload.repository.ImageRepository;
import com.fileUpload.service.ImageService;

import jakarta.transaction.Transactional;



@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired(required = false)
	private ImageRepository imageRepository;

	@Override
	public void uploadFile(MultipartFile file) throws IOException {
		FileUpload image = new FileUpload();
		 image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		 image.setContentType(file.getContentType());
		 image.setData(file.getBytes());
		 image.setSize(file.getSize());

	        imageRepository.save(image);
	        
	       
	}

	@Override
	@Transactional
	public Optional<FileUpload> getImageByUuid(String uuid) {
		// TODO Auto-generated method stub
		return imageRepository.findById(uuid);
	}

	@Override
	public List<FileUpload> getAllImages() {
		// TODO Auto-generated method stub
		return imageRepository.findAll();
	}

}
