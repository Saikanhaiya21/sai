package com.fileUpload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileUpload.model.FileUpload;

@Repository
public interface ImageRepository extends JpaRepository<FileUpload, String>{

}
