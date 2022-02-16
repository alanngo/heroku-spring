package com.example.heroku.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileIOService {
    void uploadFile(MultipartFile file) throws Exception;

    List<Map<String, Object>> parseExcelFile(MultipartFile payload) throws Exception;
}
