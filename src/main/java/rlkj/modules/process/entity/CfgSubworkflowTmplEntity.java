package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 子流程模版表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_cfg_subworkflow_tmpl")
public class CfgSubworkflowTmplEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 主流程ID
	 */
	private Integer mainFlowId;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 子流程步骤名称
	 */
	private String subStepName;
	/**
	 * 步骤内容
	 */
	private String content;
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
	 * 设置：主流程ID
	 */
	public void setMainFlowId(Integer mainFlowId) {
		this.mainFlowId = mainFlowId;
	}
	/**
	 * 获取：主流程ID
	 */
	public Integer getMainFlowId() {
		return mainFlowId;
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
	 * 设置：子流程步骤名称
	 */
	public void setSubStepName(String subStepName) {
		this.subStepName = subStepName;
	}
	/**
	 * 获取：子流程步骤名称
	 */
	public String getSubStepName() {
		return subStepName;
	}
	/**
	 * 设置：步骤内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：步骤内容
	 */
	public String getContent() {
		return content;
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
