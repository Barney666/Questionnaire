package com.computer.network.service;

import com.computer.network.vo.QuestionVO;
import com.computer.network.vo.ResponseVO;

import java.util.List;

public interface QuestionService {

    ResponseVO addQuestions(List<QuestionVO> questionVOList);

    ResponseVO updateQuestion(QuestionVO questionVO);

    ResponseVO deleteQuestion(int questionId);
}
