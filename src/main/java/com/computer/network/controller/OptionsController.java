package com.computer.network.controller;

import com.computer.network.service.OptionsService;
import com.computer.network.vo.OptionsVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionsController {
    @Autowired
    OptionsService optionsService;

    @PostMapping("/addOptions")
    public ResponseVO addOptions(@RequestBody List<OptionsVO> optionsVOList){
        return optionsService.addOptions(optionsVOList);
    }

    @PostMapping("/updateOption")
    public ResponseVO updateOption(@RequestBody OptionsVO optionsVO){
        return optionsService.updateOption(optionsVO);
    }

    @GetMapping("/{id}/deleteOption")
    public ResponseVO deleteOption(@PathVariable int id){
        return optionsService.deleteOption(id);
    }
}
