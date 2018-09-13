package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizWorktaskListEntity;

import java.util.List;
import java.util.Map;

/**
 * 工作任务清单表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface BizWorktaskListService extends IService<BizWorktaskListEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过工作票id获取工作任务清单
     * @param workSheetId
     * @return
     */
    List<BizWorktaskListEntity> getListByWorkSheetId(Integer workSheetId);

}

