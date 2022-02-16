package com.example.heroku.api;

import com.example.heroku.service.FileIOService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.*;

@RestController
@CrossOrigin
@Slf4j
public class WelcomeApi
{
    @Autowired
    FileIOService fileIOService;

    @GetMapping("/")
    public Map<String, Object> welcome()
    {
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", "Hello World");
        return ret;
    }

    @PostMapping("/")
    public  Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        fileIOService.uploadFile(file);
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", "Successful upload");
        return ret;
    }
    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile() throws Exception {
        File download = new File("download.txt");

        try(Writer w = new FileWriter(download))
        {
            w.write("Somebody once told me");
        }
        Resource resource = new InputStreamResource(new FileInputStream(download));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/regurgitate")
    public ResponseEntity<Resource> loadFile(@RequestParam("file") MultipartFile payload)
    {
        Resource resource = payload.getResource();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/excel")
    public ResponseEntity<List<Map<String, Object>>> loadExcel(@RequestParam("file") MultipartFile payload) throws Exception {


        List<Map<String, Object>> ret = fileIOService.parseExcelFile(payload);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
