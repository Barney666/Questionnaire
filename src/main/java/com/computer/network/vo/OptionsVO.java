package com.computer.network.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsVO {
    private Integer id;
    private Integer questionId;
    private String content;
}