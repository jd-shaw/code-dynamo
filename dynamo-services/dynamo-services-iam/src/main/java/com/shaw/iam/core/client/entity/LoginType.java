package com.shaw.iam.core.client.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.client.convert.LoginTypeConvert;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;
import com.shaw.mysql.jpa.entity.BaseDomain;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 登录方式
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "iam_login_type")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LoginType extends BaseDomain implements EntityBaseFunction<LoginTypeDto> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * password 密码登录, openId 第三方登录
     */
    private String type;

    /**
     * 在线时长 分钟
     */
    private Long timeout;

    /**
     * 是否需要验证码
     */
    @Getter(onMethod_ = {@Column(columnDefinition = "int default 0")})
    private int captcha;

    /**
     * 是否系统内置
     */
    @Getter(onMethod_ = {@Column(columnDefinition = "int default 0")})
    private int isSystem;

    /**
     * 密码错误几次冻结 -1表示不限制
     */
    @Getter(onMethod_ = {@Column(columnDefinition = "int default 0")})
    private int pwdErrNum;

    /**
     * 是否可用
     */
    @Getter(onMethod_ = {@Column(columnDefinition = "int default 0")})
    private int enable;

    /**
     * 描述
     */
    private String description;

    public static LoginType init(LoginTypeParam in) {
        return LoginTypeConvert.CONVERT.convert(in);
    }

    @Override
    public LoginTypeDto toDto() {
        return LoginTypeConvert.CONVERT.convert(this);
    }

}
