package rlkj.modules.process.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;

import rlkj.modules.process.dao.BizSubworkflowInstDao;
import rlkj.modules.process.entity.BizSubworkflowInstEntity;
import rlkj.modules.process.service.BizSubworkflowInstService;


@Service("bizSubworkflowInstService")
public class BizSubworkflowInstServiceImpl extends ServiceImpl<BizSubworkflowInstDao, BizSubworkflowInstEntity> implements BizSubworkflowInstService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BizSubworkflowInstEntity> page = this.selectPage(
                new Query<BizSubworkflowInstEntity>(params).getPage(),
                new EntityWrapper<BizSubworkflowInstEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BizSubworkflowInstEntity> getSafeListBySafeId(Integer safecontrolId) {
        return this.selectList( new EntityWrapper<BizSubworkflowInstEntity>().eq("safecontrol_id",safecontrolId).orderBy("seq"));
    }

    @Override
    public List<BizSubworkflowInstEntity> getWorkListByWorkId(Integer worktaskId) {
        return this.selectList( new EntityWrapper<BizSubworkflowInstEntity>().eq("worktask_id",worktaskId).orderBy("seq"));
    }
}
