package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizSubworkflowInstEntity;

import java.util.List;
import java.util.Map;

/**
 * 子流程实例表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
public interface BizSubworkflowInstService extends IService<BizSubworkflowInstEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过安全策略id获取安全子流程列表
     * @param safecontrolId
     * @return
     */
    List<BizSubworkflowInstEntity> getSafeListBySafeId(Integer safecontrolId);

    /**
     * 通过工作清单id获取安全子流程列表
     * @param worktaskId
     * @return
     */
    List<BizSubworkflowInstEntity> getWorkListByWorkId(Integer worktaskId);
}

