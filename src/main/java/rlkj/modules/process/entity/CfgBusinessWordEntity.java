package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务关键词字典表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-07 19:28:30
 */
@TableName("t_cfg_business_word")
public class CfgBusinessWordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 1 - 动词; 2 - 地理名词; 3 - 设备名称
	 */
	private Integer wordType;
	/**
	 * 关键词
	 */
	private String word;
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
	 * 设置：1 - 动词; 2 - 地理名词; 3 - 设备名称
	 */
	public void setWordType(Integer wordType) {
		this.wordType = wordType;
	}
	/**
	 * 获取：1 - 动词; 2 - 地理名词; 3 - 设备名称
	 */
	public Integer getWordType() {
		return wordType;
	}
	/**
	 * 设置：关键词
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
	 * 获取：关键词
	 */
	public String getWord() {
		return word;
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
