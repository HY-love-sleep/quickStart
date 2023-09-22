package com.hy.springbootquickstart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hy.springbootquickstart.entity.QueryParam;

import java.io.IOException;
import java.util.List;

/**
 * Description: 文件操作相关
 * Author: yhong
 * Date: 2023/9/20
 */
public interface FileService <T>{
    void pageToExcel(String baseUrl, String filePath) throws IOException;
}
