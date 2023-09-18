package com.hy.springbootquickstart.service;

import com.hy.springbootquickstart.Enum.CheckEnum;

import java.io.IOException;

/**
 * Description: 策略模式
 * Author: yhong
 * Date: 2023/9/17
 */
public interface ICheckStrategy {
    void check(String pathA, String pathB) throws IOException;

    CheckEnum getCheckNum();
}
