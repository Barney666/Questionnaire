package com.computer.network.serviceImpl;

import com.computer.network.mapper.PaperMapper;
import com.computer.network.po.Paper;
import com.computer.network.service.PaperService;
import com.computer.network.vo.PaperVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    private final static String INVALIDATION="问卷已失效";
    private final static String EMPTY="无效ID";
    private final static String USER_EMPTY="用户未创建任何问卷";
    @Autowired
    PaperMapper paperMapper;

    @Override
    public ResponseVO addPaper(PaperVO paperVO) {
        try {
            paperMapper.addPaper(paperVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO updatePaper(PaperVO paperVO) {
        try {
            Paper paper=paperMapper.selectByPaperId(paperVO.getId());
            if(paper==null)
                return ResponseVO.buildFailure(EMPTY);
            else if(paper.getStatus()==0 ||
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()).compareTo(paper.getEndTime())>0)
                return ResponseVO.buildFailure(INVALIDATION);    //问卷被撤销or现在的时间已经过了问卷的endTime
            else{
                paperMapper.updatePaper(paperVO);
                return ResponseVO.buildSuccess();
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO invalidatePaper(int paperId) {
        try {
            Paper paper=paperMapper.selectByPaperId(paperId);
            if(paper==null)
                return ResponseVO.buildFailure(EMPTY);
            else{
                paperMapper.invalidatePaper(paperId);
                return ResponseVO.buildSuccess();
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO getUserPapers(int userId) {
        try {
            List<PaperVO> paperIdList=paperMapper.getUserPapers(userId);
            if(paperIdList==null)
                return ResponseVO.buildFailure(USER_EMPTY);
            else
                return ResponseVO.buildSuccess(paperIdList);
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
}
