package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.CfgMainworkflowTmplDao;
import rlkj.modules.process.entity.CfgMainworkflowTmplEntity;
import rlkj.modules.process.service.CfgMainworkflowTmplService;

@Service("cfgMainworkflowTmplService")
public class CfgMainworkflowTmplServiceImpl extends ServiceImpl<CfgMainworkflowTmplDao, CfgMainworkflowTmplEntity> implements CfgMainworkflowTmplService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String stepName = (String) params.get("stepName");

        Page<CfgMainworkflowTmplEntity> page = this.selectPage(
                new Query<CfgMainworkflowTmplEntity>(params).getPage(),
                new EntityWrapper<CfgMainworkflowTmplEntity>()
                        .like(StringUtils.isNotBlank(stepName),"step_name", stepName)
        );

        return new PageUtils(page);
    }

}
