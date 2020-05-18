package cn.phorcys.oauth.db;

import cn.phorcys.framework.database.CodeGenerator;
import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:30 下午
 */
public class Generator {
    public static void main(String[] args) {
        CodeGenerator root = new CodeGenerator(DbType.MYSQL, "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://39.100.234.80:36020/phorcys-centre?useSSL=false",
                "root", "H@ndAdmin", "cn.phorcys.oauth.db");
        root.generate("sys_user");
    }
}
