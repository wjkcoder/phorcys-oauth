package cn.phorcys.oauth.dto;

import cn.phorcys.framework.commons.model.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:42 下午
 */
@Getter
@Setter
public class LoginRequestType extends BaseRequest {
    String username;
    String password;
}
