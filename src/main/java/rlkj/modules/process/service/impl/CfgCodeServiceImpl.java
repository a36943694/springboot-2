package rlkj.modules.process.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.Query;

import rlkj.modules.process.dao.CfgCodeDao;
import rlkj.modules.process.entity.CfgCodeEntity;
import rlkj.modules.process.service.CfgCodeService;


@Service("cfgCodeService")
public class CfgCodeServiceImpl extends ServiceImpl<CfgCodeDao, CfgCodeEntity> implements CfgCodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String type = (String)params.get("codeType");
        Page<CfgCodeEntity> page = this.selectPage(
                new Query<CfgCodeEntity>(params).getPage(),
                new EntityWrapper<CfgCodeEntity>().eq(StringUtils.isNotBlank(type),"code_type",Integer.parseInt(type))
        );

        return new PageUtils(page);
    }

    @Override
    public List<CfgCodeEntity> getListByType(Integer type) {
        return this.selectList(new EntityWrapper<CfgCodeEntity>().eq("code_type",type).orderBy("id"));
    }
}
