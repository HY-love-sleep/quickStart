package com.hy.springbootquickstart.service.impl;

import com.hy.springbootquickstart.Enum.CheckEnum;
import com.hy.springbootquickstart.entity.BaseDTO;
import com.hy.springbootquickstart.entity.Pet;
import com.hy.springbootquickstart.template.AbstractCheckTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Description: 模版方法之Pet类
 * Author: yhong
 * Date: 2023/9/17
 */
@Service
public class CheckPetServiceImpl extends AbstractCheckTemplate <Pet> {

    @Override
    public Pet convertLine(String line) {
        return Pet.convert(line);
    }

    @Override
    public void check(String pathA, String pathB) throws IOException {
        checkDetails(pathA, pathB);
    }

    @Override
    public CheckEnum getCheckNum() {
        return CheckEnum.PET;
    }
}
