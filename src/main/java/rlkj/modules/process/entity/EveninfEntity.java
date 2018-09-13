package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 告警信息表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_event_inf")
public class EveninfEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 告警编码
	 */
	private String eventCode;
	/**
	 * 工作票ID
	 */
	private Integer worksheetId;
	/**
	 * 子流程ID
	 */
	private Integer subworkflowId;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 告警级别
	 */
	private String severity;
	/**
	 * 处理状态:  0 - 未处理;  1 - 已处理
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
	 * 设置：告警编码
	 */
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	/**
	 * 获取：告警编码
	 */
	public String getEventCode() {
		return eventCode;
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
	 * 设置：子流程ID
	 */
	public void setSubworkflowId(Integer subworkflowId) {
		this.subworkflowId = subworkflowId;
	}
	/**
	 * 获取：子流程ID
	 */
	public Integer getSubworkflowId() {
		return subworkflowId;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：告警级别
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	/**
	 * 获取：告警级别
	 */
	public String getSeverity() {
		return severity;
	}
	/**
	 * 设置：处理状态:  0 - 未处理;  1 - 已处理
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：处理状态:  0 - 未处理;  1 - 已处理
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
