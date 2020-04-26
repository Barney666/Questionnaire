package com.computer.network.serviceImpl;

import com.computer.network.mapper.OptionsMapper;
import com.computer.network.service.OptionsService;
import com.computer.network.vo.OptionsVO;
import com.computer.network.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsServiceImpl implements OptionsService {
    private final static String INVALIDATION="无效ID";
    @Autowired
    OptionsMapper optionsMapper;

    @Override
    public ResponseVO addOptions(List<OptionsVO> optionsVOList) {
        try {
            for(OptionsVO optionsVO : optionsVOList)
                optionsMapper.addOption(optionsVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @Override
    public ResponseVO updateOption(OptionsVO optionsVO) {
        try {
            if(optionsMapper.selectById(optionsVO.getId())==null)
                return ResponseVO.buildFailure(INVALIDATION);
            optionsMapper.updateOption(optionsVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildSuccess(e.getMessage());
        }
    }

    @Override
    public ResponseVO deleteOption(int id) {
        try {
            if(optionsMapper.selectById(id)==null)
                return ResponseVO.buildFailure(INVALIDATION);
            optionsMapper.deleteOption(id);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            System.out.println(e);
            return ResponseVO.buildSuccess(e.getMessage());
        }
    }
}
