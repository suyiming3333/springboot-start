package com.corn.springboot.start.shiro.session;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CustomSessionManager
 * @Package com.corn.springboot.start.shiro.session
 * @Description: 自定义session管理
 * @date 2020/11/24 10:39
 */

public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if(sessionId instanceof WebSessionKey){
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
        if(request != null && sessionId != null){
            return (Session) request.getAttribute(sessionId.toString());
        }

        Session session = super.retrieveSession(sessionKey);
        if(request !=null && sessionId != null){
            //将session存到request里
            request.setAttribute(sessionId.toString(),session);
        }

        return session;
    }
}
