package cn.phorcys.oauth.dto;

import cn.phorcys.framework.commons.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:41 下午
 */
@Getter
@Setter
public class LoginResponseType extends BaseResponse {
    String sessionId;
}
