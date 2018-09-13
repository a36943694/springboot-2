package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.CfgWorkmoudleDao;
import rlkj.modules.process.entity.CfgWorkmoudleEntity;
import rlkj.modules.process.service.CfgWorkmoudleService;

@Service("cfgWorkmoudleService")
public class CfgWorkmoudleServiceImpl extends ServiceImpl<CfgWorkmoudleDao, CfgWorkmoudleEntity> implements CfgWorkmoudleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String safePoint = (String) params.get("safePoint");
        Integer checkMode = (Integer) params.get("checkMode");

        Page<CfgWorkmoudleEntity> page = this.selectPage(
                new Query<CfgWorkmoudleEntity>(params).getPage(),
                new EntityWrapper<CfgWorkmoudleEntity>()
                        .like(StringUtils.isNotBlank(safePoint),"safe_point", safePoint)
                        .eq(checkMode!=null,"check_mode",checkMode)
        );

        return new PageUtils(page);
    }

}
