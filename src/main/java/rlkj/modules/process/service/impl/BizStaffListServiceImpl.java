package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.BizStaffListDao;
import rlkj.modules.process.entity.BizStaffListEntity;
import rlkj.modules.process.service.BizStaffListService;

import java.util.Map;

@Service("bizStaffListService")
public class BizStaffListServiceImpl extends ServiceImpl<BizStaffListDao, BizStaffListEntity> implements BizStaffListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String userName = (String) params.get("userName");
        Integer workPosition = (Integer) params.get("workPosition");

        Page<BizStaffListEntity> page = this.selectPage(
                new Query<BizStaffListEntity>(params).getPage(),
                new EntityWrapper<BizStaffListEntity>()
                        .like(StringUtils.isNotBlank(userName),"user_name", userName)
                        .eq(workPosition!=null,"work_position",workPosition)
        );

        return new PageUtils(page);
    }

}
