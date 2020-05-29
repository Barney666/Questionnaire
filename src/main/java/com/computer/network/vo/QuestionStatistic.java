package com.computer.network.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionStatistic {
    private Integer id;
    private Integer paperId;
    private Integer type;
    private String title;
    private Integer filledInNum;    //此题填写人数
    private List<OptionStatistic> optionStatistics;
}
