package rlkj.modules.process.dao;

import rlkj.modules.process.entity.CfgBusinessWordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务关键词字典表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-07 19:28:30
 */
@Mapper
public interface CfgBusinessWordDao extends BaseMapper<CfgBusinessWordEntity> {
	
}
