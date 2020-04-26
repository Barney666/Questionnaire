package com.computer.network.mapper;

import com.computer.network.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerMapper {
    int addAnswer(AnswerVO answerVO);
}
