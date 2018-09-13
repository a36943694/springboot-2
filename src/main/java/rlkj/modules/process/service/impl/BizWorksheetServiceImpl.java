package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.BizWorksheetDao;
import rlkj.modules.process.entity.BizWorksheetEntity;
import rlkj.modules.process.service.BizWorksheetService;

@Service("bizWorksheetService")
public class BizWorksheetServiceImpl extends ServiceImpl<BizWorksheetDao, BizWorksheetEntity> implements BizWorksheetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String sheetNo = (String) params.get("sheetNo");
        String workplace = (String) params.get("workplace");
        String workGroup = (String) params.get("workGroup");

        Page<BizWorksheetEntity> page = this.selectPage(
                new Query<BizWorksheetEntity>(params).getPage(),
                new EntityWrapper<BizWorksheetEntity>()
                        .like(StringUtils.isNotBlank(sheetNo),"sheet_no", sheetNo)
                        .like(StringUtils.isNotBlank(workplace),"work_place", workplace)
                        .like(StringUtils.isNotBlank(workGroup),"work_group", workGroup)
        );

        return new PageUtils(page);
    }

    @Override
    public BizWorksheetEntity queryBySheetNo(String sheetNo) {
        List<String> orders = new ArrayList<>();
        orders.add("create_time");
        List<BizWorksheetEntity> lsTemp = this.selectList(
                new EntityWrapper<BizWorksheetEntity>().eq("sheet_no", sheetNo).
                        orderDesc(orders));
        return lsTemp.size() == 0 ? null : lsTemp.get(0);
    }
}
