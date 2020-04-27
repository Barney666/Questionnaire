package com.computer.network.mapper;

import com.computer.network.po.Options;
import com.computer.network.vo.OptionsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OptionsMapper {

    int addOption(OptionsVO optionsVO);

    void updateOption(OptionsVO optionsVO);

    Options selectById(int id);

    void deleteOption(int id);

    List<OptionsVO> selectByQuestionId(int questionId);
}
