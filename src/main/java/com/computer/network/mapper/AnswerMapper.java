package com.computer.network.mapper;

import com.computer.network.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {
    int addAnswer(AnswerVO answerVO);

    List<AnswerVO> selectByQuestionId(int questionId);

    List<String> selectUUIDbyPaper(int paperId);

    List<AnswerVO> selectAnswersByUUID(String UUID);

    String selectOption(@Param("question_id")int question_id, @Param("sequence")String sequence);
}
