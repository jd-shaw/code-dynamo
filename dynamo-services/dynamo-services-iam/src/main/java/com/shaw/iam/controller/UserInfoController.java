package com.shaw.iam.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserBaseInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Validated
@IgnoreAuth
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserInfoController {

	private final UserInfoService userInfoService;

	@Operation(summary = "账号是否被使用")
	@GetMapping("/exists-username")
	public ResResult<Boolean> existsUsername(String username) {
		return Res.ok(getUserInfoService().existsByUsername(username));
	}

	@Operation(summary = "账号是否被使用(不包含自己)")
	@GetMapping("/exists-username-not-id")
	public ResResult<Boolean> existsUsername(String username, String id) {
		return Res.ok(getUserInfoService().existsByUsernameAndIdNot(username, id));
	}

	@Operation(summary = "手机号是否被使用")
	@GetMapping("/exists-phone")
	public ResResult<Boolean> existsPhone(String phone) {
		return Res.ok(getUserInfoService().existsByPhone(phone));
	}

	@Operation(summary = "手机号是否被使用(不包含自己)")
	@GetMapping("/exists-phone-not-id")
	public ResResult<Boolean> existsPhone(String phone, String id) {
		return Res.ok(getUserInfoService().existsByPhoneAndIdNot(phone, id));
	}

	@Operation(summary = "邮箱是否被使用")
	@GetMapping("/exists-email")
	public ResResult<Boolean> existsEmail(String email) {
		return Res.ok(getUserInfoService().existsByEmail(email));
	}

	@Operation(summary = "邮箱是否被使用(不包含自己)")
	@GetMapping("/exists-email-not-id")
	public ResResult<Boolean> existsEmail(String email, String id) {
		return Res.ok(getUserInfoService().existsByEmailAndIdNot(email, id));
	}

	@Operation(summary = "注册账号")
	@PostMapping("/register")
	public ResResult<Void> register(@RequestBody UserRegisterParam param) {
		ValidationUtil.validateParam(param);
		getUserInfoService().register(param);
		return Res.ok();
	}

	@Operation(summary = "修改密码")
	@PostMapping("/update-password")
	public ResResult<Void> updatePassword(@NotBlank(message = "旧密码不能为空") String password,
			@NotBlank(message = "新密码不能为空") String newPassword) {
		getUserInfoService().updatePassword(password, newPassword);
		return Res.ok();
	}

	@Operation(summary = "绑定手机")
	@PostMapping("/bind-phone")
	public ResResult<Void> bindPhone(@NotBlank(message = "手机号不可为空") String phone,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "修改手机号")
	@PostMapping("/update-phone")
	public ResResult<Void> updatePhone(@RequestBody UserChangePhoneParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "修改邮箱")
	@PostMapping("/update-email")
	public ResResult<Void> updateEmail(@RequestBody UserChangeEmailParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "绑定邮箱")
	@PostMapping("/bind-email")
	public ResResult<Void> bindEmail(@NotBlank(message = "邮箱不可为空") @Email(message = "请输入正确的邮箱") String email,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "根据手机验证码查询账号")
	@GetMapping("/find-username-by-phone-captcha")
	public ResResult<String> findUsernameByPhoneCaptcha(@NotBlank(message = "手机号不可为空") String phone,
			@NotBlank(message = "验证码不可为空") String captcha) {
		return Res.ok();
	}

	@Operation(summary = "通过手机号重置密码")
	@PostMapping("/forget-password-by-phone")
	public ResResult<Void> forgetPasswordByPhone(@RequestBody UserForgetPhoneParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "通过邮箱重置密码")
	@PostMapping("/forget-password-by-email")
	public ResResult<Void> forgetPasswordByEmail(@RequestBody UserForgetEmailParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "查询用户安全信息")
	@GetMapping("/get-user-security-info")
	public ResResult<UserInfoDto> getUserSecurityInfo() {
		return Res.ok();
	}

	@Operation(summary = "查询用户基础信息")
	@GetMapping("/get-user-base-info")
	public ResResult<UserBaseInfoDto> getUserBaseInfo() {
		return Res.ok();
	}

	@Operation(summary = "修改用户基础信息")
	@PostMapping("/update-base-info")
	public ResResult<Void> updateBaseInfo(@RequestBody UserBaseInfoParam param) {
		return Res.ok();
	}

	@Operation(summary = "登录后获取用户信息")
	@GetMapping("/get-login-after-user-info")
	public ResResult<LoginAfterUserInfo> getLoginAfterUserInfo() {
		return Res.ok(userInfoService.getLoginAfterUserInfo());
	}

}
