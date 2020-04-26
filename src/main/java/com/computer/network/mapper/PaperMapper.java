package com.computer.network.mapper;

import com.computer.network.po.Paper;
import com.computer.network.vo.PaperVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperMapper {

    int addPaper(PaperVO paperVO);

    void updatePaper(PaperVO paperVO);

    Paper selectByPaperId(int paperId);

    void invalidatePaper(int paperId);

    List<PaperVO> getUserPapers(int userId);
}
