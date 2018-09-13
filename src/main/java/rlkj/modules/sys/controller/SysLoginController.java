package rlkj.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rlkj.common.utils.R;
import rlkj.modules.process.entity.BizMainworkflowInstEntity;
import rlkj.modules.process.service.BizMainworkflowInstService;
import rlkj.modules.process.service.BizWorksheetService;
import rlkj.modules.sys.entity.SysUserEntity;
import rlkj.modules.sys.form.SysLoginForm;
import rlkj.modules.sys.service.SysCaptchaService;
import rlkj.modules.sys.service.SysUserService;
import rlkj.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 */
@Api(description = "登录")
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private BizMainworkflowInstService bizMainworkflowInstService;
	@Autowired
	private BizWorksheetService bizWorksheetService;
	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@ApiOperation(value = "登录")
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {
//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());

		/**
		 * 当前正在进行的主流程
		 */
		BizMainworkflowInstEntity bizMainworkflowInstEntity = bizMainworkflowInstService.queryByCreateUserId(user.getUserId());
		if(bizMainworkflowInstEntity == null){
			r.put("isWork", false);
		}else {
			r.put("isWork", true);
			r.put("mainWork",bizMainworkflowInstEntity);
			r.put("sheetNo",bizWorksheetService.selectById(bizMainworkflowInstEntity.getWorksheetId()).getSheetNo());
		}
		return r;
	}


	/**
	 * 退出
	 */
	@ApiOperation(value = "退出")
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
