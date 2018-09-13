package rlkj.modules.process.service;

import com.baomidou.mybatisplus.service.IService;
import rlkj.common.utils.PageUtils;
import rlkj.modules.process.entity.BizWorksheetEntity;

import java.util.Map;

/**
 * 工作票配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
public interface BizWorksheetService extends IService<BizWorksheetEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过工作票编号查询工作票信息
     * @param sheetNo
     * @return
     */
    BizWorksheetEntity queryBySheetNo(String sheetNo);

}

