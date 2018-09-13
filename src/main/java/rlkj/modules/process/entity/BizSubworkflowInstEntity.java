package rlkj.modules.process.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 子流程实例表
 * 
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@TableName("t_biz_subworkflow_inst")
@Data
public class BizSubworkflowInstEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工作票id
	 */
	private Integer worksheetId;

	private Integer createUserId;
	/**
	 * 主流程id
	 */
	private Integer mainFlowId;
	/**
	 * 安全策略id
	 */
	private Integer safecontrolId;
	/**
	 * 工作清单id
	 */
	private Integer worktaskId;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 子流程步骤
	 */
	private String subStepName;
	/**
	 * 判断标准:  0 - false; 1 - true
	 */
	private Integer standard;
	/**
	 * 0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常
	 */
	private Integer state;
	/**
	 * 
	 */
	private String stateDesc;
	/**
	 * 工作内容
	 */
	private String workPrompt;
	/**
	 * 工作内容关键字
	 */
	private String safePoint;
	/**
	 * 1 - 图片;  2 - 视频;  3 - 手工
	 */
	private Integer checkMode;
	/**
	 * 照片保存路径
	 */
	private String photoPath;
	/**
	 * 视频保存路径
	 */
	private String videoPath;
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
	 * 设置：主流程id
	 */
	public void setMainFlowId(Integer mainFlowId) {
		this.mainFlowId = mainFlowId;
	}
	/**
	 * 获取：主流程id
	 */
	public Integer getMainFlowId() {
		return mainFlowId;
	}
	/**
	 * 设置：安全策略id
	 */
	public void setSafecontrolId(Integer safecontrolId) {
		this.safecontrolId = safecontrolId;
	}
	/**
	 * 获取：安全策略id
	 */
	public Integer getSafecontrolId() {
		return safecontrolId;
	}
	/**
	 * 设置：工作清单id
	 */
	public void setWorktaskId(Integer worktaskId) {
		this.worktaskId = worktaskId;
	}
	/**
	 * 获取：工作清单id
	 */
	public Integer getWorktaskId() {
		return worktaskId;
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
	 * 设置：子流程步骤
	 */
	public void setSubStepName(String subStepName) {
		this.subStepName = subStepName;
	}
	/**
	 * 获取：子流程步骤
	 */
	public String getSubStepName() {
		return subStepName;
	}
	/**
	 * 设置：判断标准:  0 - false; 1 - true
	 */
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
	/**
	 * 获取：判断标准:  0 - false; 1 - true
	 */
	public Integer getStandard() {
		return standard;
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
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
	/**
	 * 获取：
	 */
	public String getStateDesc() {
		return stateDesc;
	}
	/**
	 * 设置：工作内容
	 */
	public void setWorkPrompt(String workPrompt) {
		this.workPrompt = workPrompt;
	}
	/**
	 * 获取：工作内容
	 */
	public String getWorkPrompt() {
		return workPrompt;
	}
	/**
	 * 设置：工作内容关键字
	 */
	public void setSafePoint(String safePoint) {
		this.safePoint = safePoint;
	}
	/**
	 * 获取：工作内容关键字
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
	 * 设置：照片保存路径
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	/**
	 * 获取：照片保存路径
	 */
	public String getPhotoPath() {
		return photoPath;
	}
	/**
	 * 设置：视频保存路径
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	/**
	 * 获取：视频保存路径
	 */
	public String getVideoPath() {
		return videoPath;
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
