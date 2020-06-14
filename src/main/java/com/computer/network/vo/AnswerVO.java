package com.computer.network.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerVO {
    private Integer id;
    private Integer paperId;
    private Integer questionId;
    private Integer questionType;
    private String createTime;
    private String answerContent;
    private String user_uuid; // 前端不知道，后端生成的，用于添加答案时候用
}
