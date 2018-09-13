package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作模型配置表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_cfg_workmoudle")
public class CfgWorkmoudleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工作类型ID
	 */
	private Integer workTypeId;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 工作检查内容
	 */
	private String content;
	/**
	 * 工作检查内容关键字
	 */
	private String safePoint;
	/**
	 * 1 - 图片;  2 - 视频;  3 - 手工
	 */
	private Integer checkMode;
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
	 * 设置：工作类型ID
	 */
	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}
	/**
	 * 获取：工作类型ID
	 */
	public Integer getWorkTypeId() {
		return workTypeId;
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
	 * 设置：工作检查内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：工作检查内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：工作检查内容关键字
	 */
	public void setSafePoint(String safePoint) {
		this.safePoint = safePoint;
	}
	/**
	 * 获取：工作检查内容关键字
	 */
	public String getSafePoint() {
		return safePoint;
	}
	/**
	 * 设置：1 - 图片;  2 - 视频;  3 - 手工
	 */
	public void setCheckMode(Integer checkMode) {
		this.checkMode = checkMode;
	}
	/**
	 * 获取：1 - 图片;  2 - 视频;  3 - 手工
	 */
	public Integer getCheckMode() {
		return checkMode;
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
