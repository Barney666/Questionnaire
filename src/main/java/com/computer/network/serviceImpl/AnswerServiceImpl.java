package com.computer.network.serviceImpl;

import com.computer.network.mapper.AnswerMapper;
import com.computer.network.service.AnswerService;
import com.computer.network.vo.AnswerVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public ResponseVO addAnswers(List<AnswerVO> answerVOList) {
        try {
            for(AnswerVO answerVO:answerVOList)
                answerMapper.addAnswer(answerVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
}
