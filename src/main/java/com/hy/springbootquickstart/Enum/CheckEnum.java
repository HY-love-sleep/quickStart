package com.hy.springbootquickstart.Enum;

import lombok.Data;

/**
 * Description: chek类型枚举类
 * Author: yhong
 * Date: 2023/9/17
 */

public enum CheckEnum {

    USER("user", 1),
    PET("pet", 2);

    private final String type;
    private final int code;

    CheckEnum(String type, int code) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

}
