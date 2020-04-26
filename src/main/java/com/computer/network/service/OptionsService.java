package com.computer.network.service;

import com.computer.network.vo.OptionsVO;
import com.computer.network.vo.ResponseVO;

import java.util.List;

public interface OptionsService {

    ResponseVO addOptions(List<OptionsVO> optionsVOList);

    ResponseVO updateOption(OptionsVO optionsVO);

    ResponseVO deleteOption(int id);
}

