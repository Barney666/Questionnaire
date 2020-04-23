package com.computer.network.po;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    private Integer id;
    private Integer paperId;
    private Integer type;
    private String title;
}
