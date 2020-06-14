package com.computer.network.service;

import com.computer.network.vo.AnswerVO;
import com.computer.network.vo.ResponseVO;

import java.util.List;

public interface AnswerService {
    ResponseVO addAnswers(List<AnswerVO> answerVOList);

    /**
     * 根据paperId，搜索每条相关的回答
     * @param paperId
     * @return
     */
    ResponseVO reviewAnswers(int paperId);
}
