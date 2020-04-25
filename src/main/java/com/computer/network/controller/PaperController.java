package com.computer.network.controller;

import com.computer.network.service.PaperService;
import com.computer.network.vo.PaperVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Autowired
    PaperService paperService;

    @PostMapping("/addPaper")
    public ResponseVO addPaper(@RequestBody PaperVO paperVO){
        return paperService.addPaper(paperVO);
    }

    @PostMapping("/updatePaper")
    public ResponseVO updatePaper(@RequestBody PaperVO paperVO){
        return paperService.updatePaper(paperVO);
    }

    @GetMapping("/{paperId}/invalidatePaper")
    public ResponseVO invalidatePaper(@PathVariable Integer paperId){
        return paperService.invalidatePaper(paperId);
    }

    @GetMapping("/{userId}/getUserPapers")
    public ResponseVO getUserPapers(@PathVariable Integer userId){
        return paperService.getUserPapers(userId);
    }

}
