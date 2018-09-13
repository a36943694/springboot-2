package rlkj.modules.process.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;

import rlkj.modules.process.dao.CfgBusinessWordDao;
import rlkj.modules.process.entity.CfgBusinessWordEntity;
import rlkj.modules.process.service.CfgBusinessWordService;


@Service("cfgBusinessWordService")
public class CfgBusinessWordServiceImpl extends ServiceImpl<CfgBusinessWordDao, CfgBusinessWordEntity> implements CfgBusinessWordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CfgBusinessWordEntity> page = this.selectPage(
                new Query<CfgBusinessWordEntity>(params).getPage(),
                new EntityWrapper<CfgBusinessWordEntity>()
        );

        return new PageUtils(page);
    }

}
