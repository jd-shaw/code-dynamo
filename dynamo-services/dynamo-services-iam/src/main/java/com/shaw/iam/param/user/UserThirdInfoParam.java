package com.shaw.iam.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户三方登录绑定详情
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Schema(title = "用户三方登录绑定详情")
@Accessors(chain = true)
public class UserThirdInfoParam {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "第三方终端类型")
    private String clientCode;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

}
