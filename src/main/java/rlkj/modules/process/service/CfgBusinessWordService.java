package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.CfgBusinessWordEntity;

import java.util.Map;

/**
 * 业务关键词字典表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-07 19:28:30
 */
public interface CfgBusinessWordService extends IService<CfgBusinessWordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

