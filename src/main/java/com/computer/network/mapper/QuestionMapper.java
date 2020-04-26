package com.computer.network.mapper;

import com.computer.network.po.Question;
import com.computer.network.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {

    int addQuestion(QuestionVO questionVO);

    void updateQuestion(QuestionVO questionVO);

    Question selectByQuestionId(int id);

    void deleteQuestion(int quesitonId);
}
