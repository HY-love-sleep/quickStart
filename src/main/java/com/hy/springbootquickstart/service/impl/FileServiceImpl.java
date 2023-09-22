package com.hy.springbootquickstart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.springbootquickstart.entity.AlarmRule;
import com.hy.springbootquickstart.entity.QueryParam;
import com.hy.springbootquickstart.service.FileService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 实现类
 * Author: yhong
 * Date: 2023/9/20
 * todo:分页写入excel避免内存占用过大
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService<AlarmRule> {
    @Value("${file.Authorization}")
    String Authorization;
    @Value("${file.Cookie}")
    String Cookie;

    @Override
    public void pageToExcel(String baseUrl, String filePath) throws IOException {
        QueryParam queryParam = new QueryParam.Builder()
                .withCategory("database")
                .withSort("id%2Cdesc")
                .build();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Alarm Rules");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("规则名称");
            headerRow.createCell(1).setCellValue("描述");
            headerRow.createCell(2).setCellValue("是否暂停");
            int pageSize = 10;
            int currentPage = 0;
            int rowNum = 1;

            while (true) {
                List<AlarmRule> alarmRuleList = getData(baseUrl, queryParam, currentPage, pageSize);
                if (null == alarmRuleList || alarmRuleList.isEmpty()) {
                    break;
                }
                for (AlarmRule alarm : alarmRuleList) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(alarm.getName());
                    row.createCell(1).setCellValue(alarm.getDescription());
                    row.createCell(2).setCellValue(alarm.getPause() ? "0" : "1");
                }
                currentPage++;
            }
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                log.info("written excel successful");
            } catch (Exception e) {
                log.error("error: {}", e.getMessage());
            }
        }
    }

    List<AlarmRule> getData(String baseUrl, QueryParam params, int currentPage, int pageSize) {
        OkHttpClient client = new OkHttpClient();
        List<AlarmRule> res = new ArrayList<>();

        try {
            String queryParam = "?page=" + currentPage + "&size=" + pageSize +
                    "&category=" + params.getCategory() + "&sort=" + params.getSort();
            Request request = new Request.Builder()
                    .url(baseUrl + queryParam)
                    .header("Authorization", Authorization)
                    .header("Cookie", Cookie)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonObject = JSON.parseObject(responseBody);
                JSONArray content = jsonObject.getJSONArray("content");
                if (content == null || content.isEmpty()) {
                    return null;
                }
                // 将JSONArray转为List
                for (int i = 0; i < content.size(); i++) {
                    JSONObject data = content.getJSONObject(i);
                    String id = data.getString("id");
                    String name = data.getString("name");
                    String description = data.getString("description");
                    String isPause = data.getString("isPause");
                    AlarmRule alarmRule = new AlarmRule(Integer.valueOf(id), name, description, Boolean.parseBoolean(isPause));
                    res.add(alarmRule);
                }
            } else {
                log.error("Request failed with code: " + response.code());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}
