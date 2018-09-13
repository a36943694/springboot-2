package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.CfgSubworkflowTmplDao;
import rlkj.modules.process.entity.CfgSubworkflowTmplEntity;
import rlkj.modules.process.service.CfgSubworkflowTmplService;

import java.util.Map;

@Service("cfgSubworkflowTmplService")
public class CfgSubworkflowTmplServiceImpl extends ServiceImpl<CfgSubworkflowTmplDao, CfgSubworkflowTmplEntity> implements CfgSubworkflowTmplService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String subStepName = (String) params.get("subStepName");

        Page<CfgSubworkflowTmplEntity> page = this.selectPage(
                new Query<CfgSubworkflowTmplEntity>(params).getPage(),
                new EntityWrapper<CfgSubworkflowTmplEntity>()
                        .like(StringUtils.isNotBlank(subStepName),"sub_step_name", subStepName)
        );

        return new PageUtils(page);
    }

}
