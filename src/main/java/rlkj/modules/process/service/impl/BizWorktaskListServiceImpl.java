package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.BizWorktaskListDao;
import rlkj.modules.process.entity.BizWorktaskListEntity;
import rlkj.modules.process.service.BizWorktaskListService;

@Service("bizWorktaskListService")
public class BizWorktaskListServiceImpl extends ServiceImpl<BizWorktaskListDao, BizWorktaskListEntity> implements BizWorktaskListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BizWorktaskListEntity> page = this.selectPage(
                new Query<BizWorktaskListEntity>(params).getPage(),
                new EntityWrapper<BizWorktaskListEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BizWorktaskListEntity> getListByWorkSheetId(Integer workSheetId) {
        return this.selectList(new EntityWrapper<BizWorktaskListEntity>().eq("worksheet_id",workSheetId).orderBy("seq"));
    }
}
