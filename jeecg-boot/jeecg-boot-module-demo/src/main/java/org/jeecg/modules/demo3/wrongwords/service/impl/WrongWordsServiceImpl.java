package org.jeecg.modules.demo3.wrongwords.service.impl;

import org.jeecg.modules.demo3.wrongwords.entity.WrongWords;
import org.jeecg.modules.demo3.wrongwords.mapper.WrongWordsMapper;
import org.jeecg.modules.demo3.wrongwords.service.IWrongWordsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: wrong_words
 * @Author: jeecg-boot
 * @Date:   2021-01-06
 * @Version: V1.0
 */
@Service
public class WrongWordsServiceImpl extends ServiceImpl<WrongWordsMapper, WrongWords> implements IWrongWordsService {

}
