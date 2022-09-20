package provider.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 功能说明：websocket连接的拦截器
 * 有两种方式
 * 一种是实现接口HandshakeInterceptor，实现beforeHandshake和afterHandshake函数
 * 一种是继承HttpSessionHandshakeInterceptor，重载beforeHandshake和afterHandshake函数
 * 参照spring官方文档中的继承HttpSessionHandshakeInterceptor的方式
 */
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    Logger log = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

    /**
     * 从请求中获取唯一标记参数，填充到数据传递容器attributes
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param wsHandler
     * @param attributes         session中的attribute
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (getSession(serverHttpRequest) != null) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpServletRequest request = servletRequest.getServletRequest();
            /*从请求路径中获取自定义数据*/
            if (request.getParameter("webSocketId") != null && request.getParameter("loginTag") != null && request.getParameter("userNo") != null) {
                String webSocketId = request.getParameter("webSocketId");
                String singleLogin = request.getParameter("singleLogin");
                String loginTag = request.getParameter("loginTag");
                String userNo = request.getParameter("userNo");
                attributes.put("webSocketId", webSocketId);
                attributes.put("loginTag", loginTag);
                attributes.put("singleLogin", singleLogin);
                attributes.put("userNo", userNo);
                log.info("userNo:" + userNo + "webSocketId: " + webSocketId + " loginTag: " + loginTag + " 尝试Websocket连接");
            } else {
                return false;
            }
        }
        /*自定义数据存放在session中*/
        super.beforeHandshake(serverHttpRequest, serverHttpResponse, wsHandler, attributes);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

    private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession();
        }
        return null;
    }

}