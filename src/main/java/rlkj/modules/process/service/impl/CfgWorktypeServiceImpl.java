package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.CfgWorktypeDao;
import rlkj.modules.process.entity.CfgWorktypeEntity;
import rlkj.modules.process.service.CfgWorktypeService;

@Service("cfgWorktypeService")
public class CfgWorktypeServiceImpl extends ServiceImpl<CfgWorktypeDao, CfgWorktypeEntity> implements CfgWorktypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");

        Page<CfgWorktypeEntity> page = this.selectPage(
                new Query<CfgWorktypeEntity>(params).getPage(),
                new EntityWrapper<CfgWorktypeEntity>()
                        .like(StringUtils.isNotBlank(name),"name", name)
        );

        return new PageUtils(page);
    }

}
