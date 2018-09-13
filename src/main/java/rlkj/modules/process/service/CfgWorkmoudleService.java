package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.CfgWorkmoudleEntity;

import java.util.Map;

/**
 * 工作模型配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface CfgWorkmoudleService extends IService<CfgWorkmoudleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

