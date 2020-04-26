package com.computer.network.controller;

import com.computer.network.service.QuestionService;
import com.computer.network.vo.QuestionVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestions")
    public ResponseVO addQuestions(@RequestBody List<QuestionVO> questionVOList){
        return questionService.addQuestions(questionVOList);
    }

    @PostMapping("/updateQuestion")
    public ResponseVO updateQuestion(@RequestBody QuestionVO questionVO){
        return questionService.updateQuestion(questionVO);
    }

    @GetMapping("/{questionId}/deleteQuestion")
    public ResponseVO deleteQuestion(@PathVariable int questionId){
        return questionService.deleteQuestion(questionId);
    }
}
