package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_biz_worksheet")
@Data
public class BizWorksheetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
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

//	/**
//	 * 设置：
//	 */
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	/**
//	 * 获取：
//	 */
//	public Integer getId() {
//		return id;
//	}
//	/**
//	 * 设置：工作票编号
//	 */
//	public void setSheetNo(String sheetNo) {
//		this.sheetNo = sheetNo;
//	}
//	/**
//	 * 获取：工作票编号
//	 */
//	public String getSheetNo() {
//		return sheetNo;
//	}
//	/**
//	 * 设置：工作单位
//	 */
//	public void setWorkplace(String workplace) {
//		this.workplace = workplace;
//	}
//	/**
//	 * 获取：工作单位
//	 */
//	public String getWorkplace() {
//		return workplace;
//	}
//	/**
//	 * 设置：班组
//	 */
//	public void setWorkGroup(String workGroup) {
//		this.workGroup = workGroup;
//	}
//	/**
//	 * 获取：班组
//	 */
//	public String getWorkGroup() {
//		return workGroup;
//	}
//	/**
//	 * 设置：工作计划开始时间
//	 */
//	public void setPlanStartTime(Date planStartTime) {
//		this.planStartTime = planStartTime;
//	}
//	/**
//	 * 获取：工作计划开始时间
//	 */
//	public Date getPlanStartTime() {
//		return planStartTime;
//	}
//	/**
//	 * 设置：工作计划结束时间
//	 */
//	public void setPlanEndTime(Date planEndTime) {
//		this.planEndTime = planEndTime;
//	}
//	/**
//	 * 获取：工作计划结束时间
//	 */
//	public Date getPlanEndTime() {
//		return planEndTime;
//	}
//	/**
//	 * 设置：描述
//	 */
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	/**
//	 * 获取：描述
//	 */
//	public String getDescription() {
//		return description;
//	}
//	/**
//	 * 设置：
//	 */
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//	/**
//	 * 获取：
//	 */
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//	/**
//	 * 设置：
//	 */
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//	/**
//	 * 获取：
//	 */
//	public Date getCreateTime() {
//		return createTime;
//	}
}
