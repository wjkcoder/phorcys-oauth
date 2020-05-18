package cn.phorcys.oauth.db.serviceImpl;

import cn.phorcys.oauth.db.entity.SysUser;
import cn.phorcys.oauth.db.mapper.SysUserMapper;
import cn.phorcys.oauth.db.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wonder
 * @since 2020-05-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
