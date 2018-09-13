package rlkj.modules.process.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;

import rlkj.modules.process.dao.BizSafecontrolListDao;
import rlkj.modules.process.entity.BizSafecontrolListEntity;
import rlkj.modules.process.service.BizSafecontrolListService;


@Service("bizSafecontrolListService")
public class BizSafecontrolListServiceImpl extends ServiceImpl<BizSafecontrolListDao, BizSafecontrolListEntity> implements BizSafecontrolListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BizSafecontrolListEntity> page = this.selectPage(
                new Query<BizSafecontrolListEntity>(params).getPage(),
                new EntityWrapper<BizSafecontrolListEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BizSafecontrolListEntity> getListByWorkSheetId(Integer workSheetId) {
        return this.selectList(new EntityWrapper<BizSafecontrolListEntity>().eq("worksheet_id",workSheetId).orderBy("seq"));
    }
}
