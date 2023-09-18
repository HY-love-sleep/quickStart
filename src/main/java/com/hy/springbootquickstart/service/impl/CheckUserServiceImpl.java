package com.hy.springbootquickstart.service.impl;

import com.hy.springbootquickstart.Enum.CheckEnum;
import com.hy.springbootquickstart.entity.BaseDTO;
import com.hy.springbootquickstart.entity.User;
import com.hy.springbootquickstart.template.AbstractCheckTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Description: 校验user表是否一致
 * Author: yhong
 * Date: 2023/9/17
 */
@Service
public class CheckUserServiceImpl extends AbstractCheckTemplate<User> {

    @Override
    public User convertLine(String line) {
        return User.convert(line);
    }

    @Override
    public void check(String pathA, String pathB) throws IOException {
        checkDetails(pathA, pathB);
    }

    @Override
    public CheckEnum getCheckNum() {
        return CheckEnum.USER;
    }
}
