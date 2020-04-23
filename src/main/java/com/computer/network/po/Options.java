package com.computer.network.po;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Options {
    private Integer id;
    private Integer questionId;
    private String content;
}
