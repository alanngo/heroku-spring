package com.example.heroku.service;

import com.example.heroku.helper.ExcelHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileIOServiceImpl implements FileIOService
{
    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\youtube\\"+file.getOriginalFilename()));
    }

    @Override
    public List<Object> parseExcelFile(MultipartFile payload, String sheetName) throws Exception {
        return ExcelHelper.parseExcelFile(payload, sheetName);
    }


}
