package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizMainworkflowInstEntity;

import java.util.Map;

/**
 * 主流程实例表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface BizMainworkflowInstService extends IService<BizMainworkflowInstEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询当前用户正在进行中的主流程
     * @param createUserId
     * @return
     */
    BizMainworkflowInstEntity queryByCreateUserId(Long createUserId);
}

