package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 安全措施清单表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@TableName("t_biz_safecontrol_list")
@Data
public class BizSafecontrolListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工作票id
	 */

	@TableField(exist=false)
	private Integer safeControlType;



	private Integer worksheetId;
	/**
	 * 数据字典类型
	 */
	private Integer codeType;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 安全措施内容
	 */
	private String content;
	/**
	 * 安全措施关键字
	 */
	private String safePoint;
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
	 * 设置：工作票id
	 */
	public void setWorksheetId(Integer worksheetId) {
		this.worksheetId = worksheetId;
	}
	/**
	 * 获取：工作票id
	 */
	public Integer getWorksheetId() {
		return worksheetId;
	}
	/**
	 * 设置：数据字典类型
	 */
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	/**
	 * 获取：数据字典类型
	 */
	public Integer getCodeType() {
		return codeType;
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
	 * 设置：安全措施内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：安全措施内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：安全措施关键字
	 */
	public void setSafePoint(String safePoint) {
		this.safePoint = safePoint;
	}
	/**
	 * 获取：安全措施关键字
	 */
	public String getSafePoint() {
		return safePoint;
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
