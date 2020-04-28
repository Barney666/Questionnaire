package com.computer.network.serviceImpl;

import com.computer.network.mapper.AnswerMapper;
import com.computer.network.mapper.OptionsMapper;
import com.computer.network.mapper.PaperMapper;
import com.computer.network.mapper.QuestionMapper;
import com.computer.network.po.Paper;
import com.computer.network.service.PaperService;
import com.computer.network.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    private final static String INVALIDATION="问卷已失效";
    private final static String EMPTY="无效ID";
    private final static String USER_EMPTY="用户未创建任何问卷";
    @Autowired
    PaperMapper paperMapper;
    @Autowired    //注意每个都得Autowired 不能只写一个
    QuestionMapper questionMapper;
    @Autowired
    OptionsMapper optionsMapper;
    @Autowired
    AnswerMapper answerMapper;

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
            else if(paper.getStatus()==0 || new SimpleDateFormat("yyyy-MM-dd").format(new Date()).compareTo(paper.getEndTime())>0)
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

    @Override
    public ResponseVO checkPaper(int paperId) {
        try {
            Paper paper=paperMapper.selectByPaperId(paperId);
            if(paper==null)
                return ResponseVO.buildFailure(EMPTY);
            else if(paper.getStatus()==0 || new SimpleDateFormat("yyyy-MM-dd").format(new Date()).compareTo(paper.getEndTime())>0)
                return ResponseVO.buildFailure(INVALIDATION);
            else{
                //泛型表示的每个List：第一个放QuestionVO，第二个放List<OptionVO>
                List<List> allPaperData=new ArrayList<>();
                List<QuestionVO> questionVOList=questionMapper.selectByPaperId(paperId);
                for(QuestionVO questionVO:questionVOList){
                    int questionId=questionVO.getId();
                    List<OptionsVO> optionsVOList=optionsMapper.selectByQuestionId(questionId);
                    List backData=new ArrayList();
                    backData.add(questionVO);
                    backData.add(optionsVOList);
                    allPaperData.add(backData);
                }
                return ResponseVO.buildSuccess(allPaperData);
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO reviewPaper(int paperId) {
        try {
            Paper paper=paperMapper.selectByPaperId(paperId);
            if(paper==null)
                return ResponseVO.buildFailure(EMPTY);
            else if(paper.getStatus()==0 || new SimpleDateFormat("yyyy-MM-dd").format(new Date()).compareTo(paper.getEndTime())>0)
                return ResponseVO.buildFailure(INVALIDATION);
            else{
                //泛型表示的每个List：第一个放QuestionVO，第二个放List<OptionCaseVO>
                List<List> allPaperData=new ArrayList<>();

                List<QuestionVO> questionVOList=questionMapper.selectByPaperId(paperId);

                for(QuestionVO questionVO:questionVOList){

                    if(questionVO.getType()!=3){    //单选题和多选题
                        int questionId=questionVO.getId();
                        List<OptionsVO> optionsVOList=optionsMapper.selectByQuestionId(questionId);
                        List<OptionsCaseVO> optionsCaseVOList=new ArrayList<>();
                        for(OptionsVO optionsVO:optionsVOList){   //先都转成另一个VO
                            OptionsCaseVO optionsCaseVO=new OptionsCaseVO();
                            BeanUtils.copyProperties(optionsVO,optionsCaseVO);
                            optionsCaseVO.setSelectedNum(0);
                            optionsCaseVOList.add(optionsCaseVO);
                        }

                        List<AnswerVO> answerVOList=answerMapper.selectByQuestionId(questionId);

                        for(AnswerVO answerVO:answerVOList){
                            String answerContent=answerVO.getAnswerContent();
                            String[] optionIdList=answerContent.split(",");
                            for(String idStr:optionIdList){
                                int id=Integer.valueOf(idStr);
                                for(OptionsCaseVO optionsCaseVO:optionsCaseVOList){
                                    if(optionsCaseVO.getId()==id){
                                        optionsCaseVO.setSelectedNum(optionsCaseVO.getSelectedNum()+1);
                                        break;
                                    }
                                }
                            }
                        }
                        List backData=new ArrayList();
                        backData.add(questionVO);
                        backData.add(optionsCaseVOList);
                        allPaperData.add(backData);
                    }
                }
                return ResponseVO.buildSuccess(allPaperData);
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

}
