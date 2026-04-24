package com.ra.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadService {
    public final Cloudinary cloudinary;

    public String upload(MultipartFile file){
        String fileName = file.getOriginalFilename();

        if(fileName.contains(".") && fileName != null){
            fileName = fileName.substring(fileName.lastIndexOf("."));
        }

        Map uploadParams = ObjectUtils.asMap(
                "public_id",fileName
        );
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),uploadParams);
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
