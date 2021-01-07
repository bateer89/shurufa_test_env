package org.jeecg.modules.demo3.words.service.impl;

import org.jeecg.modules.demo3.words.entity.ZnUserWords;
import org.jeecg.modules.demo3.words.mapper.ZnUserWordsMapper;
import org.jeecg.modules.demo3.words.service.IZnUserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户汉字完成表
 * @Author: jeecg-boot
 * @Date:   2021-01-06
 * @Version: V1.0
 */
@Service
public class ZnUserWordsServiceImpl extends ServiceImpl<ZnUserWordsMapper, ZnUserWords> implements IZnUserWordsService {

    @Resource
    ZnUserWordsMapper znUserWordsMapper;
    @Override
    public List<ZnUserWords> queryByUserId(String userId) {
        return znUserWordsMapper.queryByUserId(userId);
    }
}
