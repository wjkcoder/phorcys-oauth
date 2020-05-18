package cn.phorcys.oauth.service.impl;

import cn.phorcys.framework.commons.exception.ExceptionUtils;
import cn.phorcys.framework.commons.utility.object.ObjectUtil;
import cn.phorcys.oauth.db.entity.SysUser;
import cn.phorcys.oauth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.baomidou.dynamic.datasource.toolkit.CryptoUtils.encrypt;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:50 下午
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public SysUser checkUserPassword(String username, String password) {
        List<SysUser> sysUsers = new SysUser().selectList(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUserName, username));
        if (ObjectUtil.isNullOrEmpty(sysUsers)) {
            return null;
        }
        if (sysUsers.size() > 1) {
            ExceptionUtils.seed("Need user one but return two");
        }
        SysUser user = sysUsers.get(0);
        return userCheckByPrimaryKey(user, password);
    }

    private SysUser userCheckByPrimaryKey(SysUser user, String password) {
        try {
            String encryptPassword = encrypt(password);
            if(encryptPassword.equals(user.getPassword())){
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
