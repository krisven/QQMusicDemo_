package com.kk.ssm.websocket;

import com.kk.ssm.entity.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.net.UnknownServiceException;
import java.util.Map;

 /*websocket握手拦截器
 * 拦截握手前，握手后的两个切面
 */


public class MyHandShakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest ) serverHttpRequest).getServletRequest().getSession(false).getAttribute("usernumber") + "]已经建立连接");
        if(serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            //标记用户
            Object usernumber = session.getAttribute("usernumber");
            if (usernumber != null) {
                map.put("usernumber", usernumber);
                System.out.println("用户id:" + usernumber + " 被加入");
            } else {
                System.out.println("user为空");
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
