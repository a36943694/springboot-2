package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 主流程实例表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_biz_mainworkflow_inst")
@Data
public class BizMainworkflowInstEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工作票ID
	 */

	private Integer createUserId;
	private Integer worksheetId;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 流程步骤名称
	 */
	private String stepName;
	/**
	 * 0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常
	 */
	private Integer state;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：工作票ID
	 */
	public void setWorksheetId(Integer worksheetId) {
		this.worksheetId = worksheetId;
	}
	/**
	 * 获取：工作票ID
	 */
	public Integer getWorksheetId() {
		return worksheetId;
	}
	/**
	 * 设置：排序
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：流程步骤名称
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	/**
	 * 获取：流程步骤名称
	 */
	public String getStepName() {
		return stepName;
	}
	/**
	 * 设置：0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
