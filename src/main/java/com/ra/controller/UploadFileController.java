package com.ra.controller;

import com.ra.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
@RequiredArgsConstructor
public class UploadFileController {
    private final UploadService uploadService;
    @PostMapping
    public ResponseEntity<?> upload(@ModelAttribute("file")MultipartFile file){
        String url = uploadService.upload(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
