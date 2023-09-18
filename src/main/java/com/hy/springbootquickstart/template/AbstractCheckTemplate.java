package com.hy.springbootquickstart.template;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.hy.springbootquickstart.entity.BaseDTO;
import com.hy.springbootquickstart.service.ICheckStrategy;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * Description: 对比张张表字段是否相同的模板方法
 * Author: yhong
 * Date: 2023/9/16
 */
public abstract class AbstractCheckTemplate <T extends BaseDTO> implements ICheckStrategy {
    private static final Logger log = LoggerFactory.getLogger(AbstractCheckTemplate.class);
    public void checkDetails(String pathOfA, String pathOfB) throws FileNotFoundException {
        // 将表转为List
        Pair<List<T>, List<T>> listPair = convertToList(pathOfA, pathOfB, this::convertLine);;

        // 将List转为Map
        Pair<Map<String, T>, Map<String, T>> mapPair = convertToMap(listPair);

        // 比较两个Map中的所有字段
        checkMapPair(mapPair);
    }


    private void checkMapPair(Pair<Map<String, T>, Map<String, T>> mapPair) {
        Map<String, T> map1 = mapPair.getKey();
        Map<String, T> map2 = mapPair.getValue();
        map1.forEach((key, value) -> {
            if (map2.containsKey(key)) {
                T valueB = map2.get(key);
                List<String> diffList;
                try {
                    diffList = compareObjects(value, valueB);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                diffList.forEach(dif -> {
                    log.info("{} is different, key is {}", dif, key);
                });
            }
        });
    }

    // 通过反射获取字段并进行比较
    private List<String> compareObjects(T valueA, T valueB) throws IllegalAccessException {
        List<String> res = new ArrayList<>();
        Class<? extends BaseDTO> aClass = valueA.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            Object a = field.get(valueA);
            Object b = field.get(valueB);
            if (!Objects.equals(a, b)) {
                res.add(name);
            }
        }
        return res;
    }

    private Pair<Map<String, T>, Map<String, T>> convertToMap(Pair<List<T>, List<T>> listPair) {
        return new Pair<>(listToMap(listPair.getKey()), listToMap(listPair.getValue()));
    }

    private Map<String, T> listToMap(List<T> list) {
        Map<String,T> map = new HashMap<>();
        list.forEach(dto -> {
            map.put(dto.getKey(), dto);
        });
        return map;
    }

    private Pair<List<T>, List<T>> convertToList(String pathOfA, String pathOfB, Function<String, T> converter) throws FileNotFoundException {
        return new Pair<>(tableToList(pathOfA, converter), tableToList(pathOfB, converter));
    }

    private List<T> tableToList(String path, Function<String, T> converter) {
        List<T> resList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resList.add(converter.apply(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resList;
    }

    // 表的每一行如何转换成实体类只能交由子类来实现了;
    public abstract T convertLine(String line);

}
