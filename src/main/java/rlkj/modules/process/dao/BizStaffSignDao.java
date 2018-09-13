package rlkj.modules.process.dao;

import rlkj.modules.process.entity.BizStaffSignEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员签到表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@Mapper
public interface BizStaffSignDao extends BaseMapper<BizStaffSignEntity> {
	
}
