package org.jeecg.modules.demo3.words.service;

import org.jeecg.modules.demo3.words.entity.ZnUserWords;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 用户任务表
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
public interface IZnUserWordsService extends IService<ZnUserWords> {
    public List<ZnUserWords> queryByUserId(String userId);
}
