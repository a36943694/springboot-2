package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作任务清单表
 * 
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@TableName("t_biz_worktask_list")
@Data
public class BizWorktaskListEntity implements Serializable {
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
	 * 排序
	 */
	private Integer seq;
	/**
	 * 工作内容
	 */
	private String content;

	/**
	 * 工作地点及设务名称
	 */
	private String workAdressDevices;
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
	 * 设置：工作内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：工作内容
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
