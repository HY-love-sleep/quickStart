package com.hy.springbootquickstart.entity;

import lombok.Data;

/**
 * Description: user
 * Author: yhong
 * Date: 2023/9/16
 */
@Data
public class User extends BaseDTO{
    private Integer id;
    private String name;
    private Integer age;

    @Override
    public String getKey() {
        return id + name;
    }

    public static User convert(String line) {
        User user = new User();
        String[] split = line.split(",", -1);
        user.setId(Integer.valueOf(split[0]));
        user.setName(split[1]);
        user.setAge(Integer.valueOf(split[2]));
        return user;
    }
}
