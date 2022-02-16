package com.example.heroku.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileIOService {
    void uploadFile(MultipartFile file) throws Exception;

    List<Object> parseExcelFile(MultipartFile payload, String sheetName) throws Exception;
}
