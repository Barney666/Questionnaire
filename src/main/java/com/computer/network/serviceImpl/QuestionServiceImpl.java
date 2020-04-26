package com.computer.network.serviceImpl;

import com.computer.network.mapper.QuestionMapper;
import com.computer.network.service.QuestionService;
import com.computer.network.vo.QuestionVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final static String INVALIDATION="无效ID";
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public ResponseVO addQuestions(List<QuestionVO> questionVOList) {
        try {
            for(QuestionVO questionVO:questionVOList)
                questionMapper.addQuestion(questionVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO updateQuestion(QuestionVO questionVO) {
        try {
            if(questionMapper.selectByQuestionId(questionVO.getId())==null)
                return ResponseVO.buildFailure(INVALIDATION);
            questionMapper.updateQuestion(questionVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO deleteQuestion(int questionId) {
        try {
            if(questionMapper.selectByQuestionId(questionId)==null)
                return ResponseVO.buildFailure(INVALIDATION);
            questionMapper.deleteQuestion(questionId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
}
