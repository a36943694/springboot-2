package rlkj.modules.process.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作票配置表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Data
@ApiModel("工作票返回结果")
public class BizWorksheetVO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@ApiModelProperty("工作票id")
	private Integer id;
	/**
	 * 工作票编号
	 */
	private String sheetNo;
	/**
	 * 工作单位
	 */
	private String workplace;
	/**
	 *工作变配电站名称
	 */
	private String workStation;
	/**
	 * 班组
	 */
	private String workGroup;
	/**
	 * 工作计划开始时间
	 */
	private Date planStartTime;
	/**
	 * 工作计划结束时间
	 */
	private Date planEndTime;
	/**
	 * 描述
	 */

	private String description;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

	private Integer state;

}
