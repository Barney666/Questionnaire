package com.computer.network.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionVO {
    private Integer id;
    private Integer paperId;
    private Integer type;
    private String title;
}
