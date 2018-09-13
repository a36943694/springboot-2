package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.CfgCodeEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
public interface CfgCodeService extends IService<CfgCodeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过类型获取列表
     * @param type 类型
     * @return
     */
    List<CfgCodeEntity> getListByType(Integer type);
}

