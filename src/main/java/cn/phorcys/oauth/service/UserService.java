package cn.phorcys.oauth.service;

import cn.phorcys.oauth.db.entity.SysUser;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:50 下午
 */
public interface UserService {
     SysUser checkUserPassword(String username, String password);
}
