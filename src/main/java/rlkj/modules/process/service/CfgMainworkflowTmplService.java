package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.CfgMainworkflowTmplEntity;

import java.util.Map;

/**
 * 主流程模版表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface CfgMainworkflowTmplService extends IService<CfgMainworkflowTmplEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

