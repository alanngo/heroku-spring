package com.example.heroku.helper;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelper {

    private static List<String> buildKeySet( Row row){
        List<String> ret = new ArrayList<>();
        row.forEach(cell -> ret.add(cell.getStringCellValue()
                .trim()
                .replace(" ", "_")
                .toLowerCase()));
        return ret;
    }
    private static Map<String, Object> buildObject(List<String> keySet, List<Object>cellValues){
        Map<String, Object> ret = new HashMap<>();

        for (int i =0; i<keySet.size(); i++)
        {
            try {ret.put(keySet.get(i), cellValues.get(i));}
            catch (IndexOutOfBoundsException e) {ret.put(keySet.get(i), null);}
        }
        return ret;
    }
    private static Map<String, Object> buildJsonObject(List<String> keys, Row row)
    {
        List<Object> cellValues = new ArrayList<>();
        row.forEach(cell ->
        {
            switch (cell.getCellType()) {
                case BOOLEAN:
                    cellValues.add(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    cellValues.add(cell.getNumericCellValue());
                    break;
                case STRING:
                    cellValues.add(cell.getStringCellValue());
                    break;
                case ERROR:
                    cellValues.add(cell.getErrorCellValue());
                    break;
                case BLANK:
                    cellValues.add(null);
                case FORMULA:
                    break;
            }
        });
        return buildObject(keys, cellValues);
    }

    public static List<Object> parseExcelFile(MultipartFile payload, String sheetName) throws IOException {
        List<Object> ret = new ArrayList<>();
        try(Workbook workbook = new XSSFWorkbook(payload.getInputStream()))
        {
            Sheet sheet = workbook.getSheet(sheetName);
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
