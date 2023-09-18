package com.hy.springbootquickstart.entity;

import lombok.Data;

/**
 * Description: 宠物
 * Author: yhong
 * Date: 2023/9/16
 */
@Data
public class Pet extends BaseDTO{
    private Integer id;
    private String name;
    private String type;

    @Override
    public String getKey() {
        return id + type + name;
    }

    public static Pet convert(String line) {
        Pet pet = new Pet();
        String[] split = line.split(",", -1);
        pet.setId(Integer.valueOf(split[0]));
        pet.setName(split[1]);
        pet.setType(split[2]);
        return pet;
    }
}
