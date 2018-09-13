package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.BizMainworkflowInstDao;
import rlkj.modules.process.entity.BizMainworkflowInstEntity;
import rlkj.modules.process.service.BizMainworkflowInstService;


@Service("bizMainworkflowInstService")
public class BizMainworkflowInstServiceImpl extends ServiceImpl<BizMainworkflowInstDao, BizMainworkflowInstEntity> implements BizMainworkflowInstService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String stepName = (String) params.get("stepName");
        Integer state = (Integer) params.get("state");
        Page<BizMainworkflowInstEntity> page = this.selectPage(
                new Query<BizMainworkflowInstEntity>(params).getPage(),
                new EntityWrapper<BizMainworkflowInstEntity>()
                        .like(StringUtils.isNotBlank(stepName),"step_name", stepName)
                        .eq(state!=null,"state",state)
        );

        return new PageUtils(page);
    }

    @Override
    public BizMainworkflowInstEntity queryByCreateUserId(Long createUserId) {
        List<String> orders = new ArrayList<>();
        orders.add("create_time");
        List<BizMainworkflowInstEntity> lsTemp = this.selectList(new EntityWrapper<BizMainworkflowInstEntity>().eq("create_user_id",createUserId)
                .eq("state",2).orderDesc(orders));
        return lsTemp.size() == 0 ? null : lsTemp.get(0);
    }
}
