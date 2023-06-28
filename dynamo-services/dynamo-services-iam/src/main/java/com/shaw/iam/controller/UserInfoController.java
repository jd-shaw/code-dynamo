package com.shaw.iam.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.shaw.iam.core.user.service.UserInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserBaseInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Validated
@IgnoreAuth
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserInfoController {

	private final UserInfoService userInfoService;

	@Operation(summary = "账号是否被使用")
	@GetMapping("/existsUsername")
	public ResResult<Boolean> existsUsername(String username) {
		return Res.ok();
	}

	@Operation(summary = "账号是否被使用(不包含自己)")
	@GetMapping("/existsUsernameNotId")
	public ResResult<Boolean> existsUsername(String username, Long id) {
		return Res.ok();
	}

	@Operation(summary = "手机号是否被使用")
	@GetMapping("/existsPhone")
	public ResResult<Boolean> existsPhone(String phone) {
		return Res.ok();
	}

	@Operation(summary = "手机号是否被使用(不包含自己)")
	@GetMapping("/existsPhoneNotId")
	public ResResult<Boolean> existsPhone(String phone, Long id) {
		return Res.ok();
	}

	@Operation(summary = "邮箱是否被使用")
	@GetMapping("/existsEmail")
	public ResResult<Boolean> existsEmail(String email) {
		return Res.ok();
	}

	@Operation(summary = "邮箱是否被使用(不包含自己)")
	@GetMapping("/existsEmailNotId")
	public ResResult<Boolean> existsEmail(String email, Long id) {
		return Res.ok();
	}

	@Operation(summary = "注册账号")
	@PostMapping("/register")
	public ResResult<Void> register(@RequestBody UserRegisterParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "修改密码")
	@PostMapping("/updatePassword")
	public ResResult<Void> updatePassword(@NotBlank(message = "旧密码不能为空") String password,
			@NotBlank(message = "新密码不能为空") String newPassword) {
		return Res.ok();
	}

	@Operation(summary = "绑定手机")
	@PostMapping("/bindPhone")
	public ResResult<Void> bindPhone(@NotBlank(message = "手机号不可为空") String phone,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "修改手机号")
	@PostMapping("/updatePhone")
	public ResResult<Void> updatePhone(@RequestBody UserChangePhoneParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "修改邮箱")
	@PostMapping("/updateEmail")
	public ResResult<Void> updateEmail(@RequestBody UserChangeEmailParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "绑定邮箱")
	@PostMapping("/bindEmail")
	public ResResult<Void> bindEmail(@NotBlank(message = "邮箱不可为空") @Email(message = "请输入正确的邮箱") String email,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "根据手机验证码查询账号")
	@GetMapping("/findUsernameByPhoneCaptcha")
	public ResResult<String> findUsernameByPhoneCaptcha(@NotBlank(message = "手机号不可为空") String phone,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "通过手机号重置密码")
	@PostMapping("/forgetPasswordByPhone")
	public ResResult<Void> forgetPasswordByPhone(@RequestBody UserForgetPhoneParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "通过邮箱重置密码")
	@PostMapping("/forgetPasswordByEmail")
	public ResResult<Void> forgetPasswordByEmail(@RequestBody UserForgetEmailParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "查询用户安全信息")
	@GetMapping("/getUserSecurityInfo")
	public ResResult<UserInfoDto> getUserSecurityInfo() {
		return Res.ok();
	}

	@Operation(summary = "查询用户基础信息")
	@GetMapping("/getUserBaseInfo")
	public ResResult<UserBaseInfoDto> getUserBaseInfo() {
		return Res.ok();
	}

	@Operation(summary = "修改用户基础信息")
	@PostMapping("/updateBaseInfo")
	public ResResult<Void> updateBaseInfo(@RequestBody UserBaseInfoParam param) {
		return Res.ok();
	}

	@Operation(summary = "登录后获取用户信息")
	@GetMapping("/getLoginAfterUserInfo")
	public ResResult<LoginAfterUserInfo> getLoginAfterUserInfo() {
		return Res.ok(userInfoService.getLoginAfterUserInfo());
	}

}
