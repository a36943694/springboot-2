package rlkj.modules.process.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;
import rlkj.modules.process.dao.EveninfDao;
import rlkj.modules.process.entity.EveninfEntity;
import rlkj.modules.process.service.EveninfService;

@Service("eveninfService")
public class EveninfServiceImpl extends ServiceImpl<EveninfDao, EveninfEntity> implements EveninfService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String eventCode = (String) params.get("eventCode");
        Integer state = (Integer) params.get("state");

        Page<EveninfEntity> page = this.selectPage(
                new Query<EveninfEntity>(params).getPage(),
                new EntityWrapper<EveninfEntity>()
                        .like(StringUtils.isNotBlank(eventCode),"event_code", eventCode)
                        .eq(state!=null,"state",state)
        );

        return new PageUtils(page);
    }

}
