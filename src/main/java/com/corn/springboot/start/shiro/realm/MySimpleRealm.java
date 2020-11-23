package com.corn.springboot.start.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MySimpleRealm
 * @Package com.corn.springboot.start.shiro
 * @Description: 自定义认证数据域(认证数据源)
 * @date 2020/11/23 12:49
 */
public class MySimpleRealm extends AuthorizingRealm {

//    @Autowired
//    private LoginService loginService;


    /**
     * 授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo  simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if("suyiming".equals(userName)){
            simpleAuthorizationInfo.addRole("ADMIN");
            simpleAuthorizationInfo.addStringPermission("all");
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证信息
     * @param authenticationToken 用户名、密码等待认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登录的用户名
        String userName = (String)authenticationToken.getPrincipal();
        //用户的正确在库密码(corn1234 md5)
        String realPassword = "6b7be4dc0e16fee721f44d270613c945";

        if("suyiming".equals(userName)){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,
                    realPassword,
                    ByteSource.Util.bytes("corn"),
                    getName());
            return simpleAuthenticationInfo;
        }else{
            return null;
        }


    }
}
