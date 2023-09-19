package com.hy.springbootquickstart.service.impl;

import com.hy.springbootquickstart.Enum.CheckEnum;
import com.hy.springbootquickstart.service.ICheckStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 工厂方法
 * Author: yhong
 * Date: 2023/9/17
 */
@Component
public class CheckCompareFactory implements ApplicationContextAware {
    private final Map<CheckEnum, ICheckStrategy> checkStrategyMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ICheckStrategy> tempMap = applicationContext.getBeansOfType(ICheckStrategy.class);
        tempMap.values().forEach(strategyService -> {
            checkStrategyMap.put(strategyService.getCheckNum(), strategyService);
        });
    }

    public void checkCompare(CheckEnum checkEnum, String pathA, String pathB) throws IOException {
        ICheckStrategy strategyService = checkStrategyMap.get(checkEnum);
        strategyService.check(pathA, pathB);
    }

}
