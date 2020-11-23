package com.corn.springboot.start.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Objects;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: PasswordMatcher
 * @Package com.corn.springboot.start.shiro.matcher
 * @Description: 自定义用户名密码校验器
 * @date 2020/11/23 13:35
 */
public class PasswordMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {


        String userName = (String)token.getPrincipal();
        String password = token.getCredentials().toString();
        /**用户token里的credentials，根据matcher指定的算法进行计算**/
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        System.out.println("token里计算后的密码:"+tokenHashedCredentials);
        /**用户存在数据库里的密码(simpletokeninfo带过来的)**/
        Object accountCredentials = getCredentials(info);
        System.out.println("用户存在数据库里的密码:"+accountCredentials);
        if(Objects.equals(tokenHashedCredentials,accountCredentials)){
            return true;
        }else{
            return false;
        }
    }
}
