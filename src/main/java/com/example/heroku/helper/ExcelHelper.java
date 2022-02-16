package com.example.heroku.helper;

import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelper {

    public static List<String> buildKeySet( Row row){
        List<String> ret = new ArrayList<>();
        row.forEach(cell -> ret.add(cell.getStringCellValue().toLowerCase()));
        return ret;
    }
    public static Map<String, Object> buildJsonObject(List<String> keys, Row row)
    {
        Map<String, Object> ret = new HashMap<>();
        List<String> keySet = new ArrayList<>(keys);
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
        for (int i =0; i<keySet.size(); i++)
            ret.put(keySet.get(i), cellValues.get(i));
        return ret;
    }
}
