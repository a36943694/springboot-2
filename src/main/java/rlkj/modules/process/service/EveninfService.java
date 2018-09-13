package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.EveninfEntity;

import java.util.Map;

/**
 * 告警信息表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface EveninfService extends IService<EveninfEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

