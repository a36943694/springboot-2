package rlkj.modules.process.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;

import rlkj.modules.process.dao.BizStaffSignDao;
import rlkj.modules.process.entity.BizStaffSignEntity;
import rlkj.modules.process.service.BizStaffSignService;


@Service("bizStaffSignService")
public class BizStaffSignServiceImpl extends ServiceImpl<BizStaffSignDao, BizStaffSignEntity> implements BizStaffSignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BizStaffSignEntity> page = this.selectPage(
                new Query<BizStaffSignEntity>(params).getPage(),
                new EntityWrapper<BizStaffSignEntity>()
        );

        return new PageUtils(page);
    }

}
