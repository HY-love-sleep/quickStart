package com.hy.springbootquickstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 告警规则
 * Author: yhong
 * Date: 2023/9/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmRule {
    private Integer id;
    private String name;
    private String description;
    private Boolean pause;
}
