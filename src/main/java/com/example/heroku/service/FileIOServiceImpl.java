package com.example.heroku.service;

import com.example.heroku.helper.ExcelHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    public List<Map<String, Object>> parseExcelFile(MultipartFile payload) throws Exception {
        List<Map<String, Object>> ret = new ArrayList<>();
        try(Workbook workbook = new XSSFWorkbook(payload.getInputStream()))
        {
            Sheet sheet = workbook.getSheetAt(0);
            List<String> keys = new ArrayList<>();

            int i =0;
            for (Row row: sheet)
            {
                if (i>0)
                {
                    Map<String, Object> tmpObject = ExcelHelper.buildJsonObject(keys, row);
                    ret.add(tmpObject);
                }
                else
                    keys= ExcelHelper.buildKeySet(row);
                i++;
            }
        }
        return ret;
    }


}
