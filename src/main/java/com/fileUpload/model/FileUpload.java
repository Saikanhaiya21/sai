package com.fileUpload.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
   private String id;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;

}
