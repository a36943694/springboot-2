package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作人员配置表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_biz_staff_list")
@Data
public class BizStaffListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工作票ID
	 */
	private Integer worksheetId;
	/**
	 * 人员名称
	 */
	private String userName;
	/**
	 * 1 - 工作负责人;  2 - 施工人员
	 */
	private Integer workPosition;
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
	 * 设置：人员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：人员名称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：1 - 工作负责人;  2 - 施工人员
	 */
	public void setWorkPosition(Integer workPosition) {
		this.workPosition = workPosition;
	}
	/**
	 * 获取：1 - 工作负责人;  2 - 施工人员
	 */
	public Integer getWorkPosition() {
		return workPosition;
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
