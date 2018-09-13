package rlkj.modules.process.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import rlkj.modules.process.entity.BizWorksheetEntity;

/**
 * 工作票配置表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Mapper
public interface BizWorksheetDao extends BaseMapper<BizWorksheetEntity> {
	
}
