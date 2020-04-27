package com.computer.network.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsCaseVO {
    private Integer id;
    private Integer questionId;
    private String content;
    private Integer selectedNum;     //多少人此问题选了这个选项
}