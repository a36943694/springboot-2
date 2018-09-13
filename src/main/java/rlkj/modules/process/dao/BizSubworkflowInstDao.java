package rlkj.modules.process.dao;

import rlkj.modules.process.entity.BizSubworkflowInstEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 子流程实例表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@Mapper
public interface BizSubworkflowInstDao extends BaseMapper<BizSubworkflowInstEntity> {
	
}
