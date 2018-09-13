package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员签到表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@TableName("t_biz_staff_sign")
public class BizStaffSignEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer subworkflowId;
	/**
	 * 工作票ID
	 */
	private Integer staffId;
	/**
	 * 0 - 未签到;  1 - 已签到
	 */
	private Integer signState;
	/**
	 * 签到时间
	 */
	private Date signTime;
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
	 * 设置：
	 */
	public void setSubworkflowId(Integer subworkflowId) {
		this.subworkflowId = subworkflowId;
	}
	/**
	 * 获取：
	 */
	public Integer getSubworkflowId() {
		return subworkflowId;
	}
	/**
	 * 设置：工作票ID
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	/**
	 * 获取：工作票ID
	 */
	public Integer getStaffId() {
		return staffId;
	}
	/**
	 * 设置：0 - 未签到;  1 - 已签到
	 */
	public void setSignState(Integer signState) {
		this.signState = signState;
	}
	/**
	 * 获取：0 - 未签到;  1 - 已签到
	 */
	public Integer getSignState() {
		return signState;
	}
	/**
	 * 设置：签到时间
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：签到时间
	 */
	public Date getSignTime() {
		return signTime;
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
