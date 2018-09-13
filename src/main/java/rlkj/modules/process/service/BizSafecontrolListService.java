package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizSafecontrolListEntity;

import java.util.List;
import java.util.Map;

/**
 * 安全措施清单表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
public interface BizSafecontrolListService extends IService<BizSafecontrolListEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过工作票Id获取安全措施清单
     * @param workSheetId
     * @return
     */
    List<BizSafecontrolListEntity> getListByWorkSheetId(Integer workSheetId);
}

