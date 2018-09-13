package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizStaffSignEntity;

import java.util.Map;

/**
 * 人员签到表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
public interface BizStaffSignService extends IService<BizStaffSignEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

