package com.shaw.iam.core.user.event;

import com.shaw.iam.dto.user.UserInfoDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 用户创建事件
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
public class UserCreateEvent extends ApplicationEvent {

    private final UserInfoDto userInfo;

    public UserCreateEvent(Object source, UserInfoDto userInfo) {
        super(source);
        this.userInfo = userInfo;
    }

}
