package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizStaffListEntity;

import java.util.Map;

/**
 * 工作人员配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface BizStaffListService extends IService<BizStaffListEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

