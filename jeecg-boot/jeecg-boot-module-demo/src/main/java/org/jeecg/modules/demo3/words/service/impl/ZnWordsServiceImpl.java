package org.jeecg.modules.demo3.words.service.impl;

import org.jeecg.modules.demo3.words.entity.ZnWords;
import org.jeecg.modules.demo3.words.mapper.ZnWordsMapper;
import org.jeecg.modules.demo3.words.service.IZnWordsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 汉字对照表
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Service
public class ZnWordsServiceImpl extends ServiceImpl<ZnWordsMapper, ZnWords> implements IZnWordsService {

}
