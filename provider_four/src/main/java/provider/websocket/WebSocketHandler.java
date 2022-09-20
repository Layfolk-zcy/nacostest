package provider.websocket;

import cn.hutool.json.JSONUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.List;

/**
 * 功能说明：WebSocket处理器
 * 可以继承 {@link TextWebSocketHandler}/{@link BinaryWebSocketHandler}，
 * 或者简单的实现{@link WebSocketHandler}接口
 */
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * 前端心跳检测发送数据
     */
    public static final String PING = "ping";
    /**
     * 返回给前端的心跳数据
     */
    public static final String PONG = "pong";
    Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    /**
     * 建立连接
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /*从session中获取指定数据*/
        String webSocketId = MapUtils.getString(session.getAttributes(), "webSocketId");
        String loginTag = MapUtils.getString(session.getAttributes(), "loginTag");
        String singleLogin = MapUtils.getString(session.getAttributes(), "singleLogin");
        String userNo = MapUtils.getString(session.getAttributes(), "userNo");
        /*判断是否已经建立连接*/
        if (WebSocketSessionUtils.hasConnectionLike(userNo, loginTag)) {
            // 启用单点登入时
            if ("1".equals(singleLogin)) {
                // webScoketId 的格式为 userNo_uuid, 所以将userNo传入进行模糊匹配
                List<WebSocketSession> existSessionList = WebSocketSessionUtils.getLike(userNo, loginTag);
                for (WebSocketSession webSocketSession : existSessionList) {
                    String existWebSocketId = (String) webSocketSession.getAttributes().get("webSocketId");
                    if (!webSocketId.equals(existWebSocketId)) {
                        // 表示当前用户同类型终端已经登录过，推送消息告知，使其被迫下线
                        HashMap<String, Object> msgMap = new HashMap<String, Object>();
                        msgMap.put("msg_type", "logout");
                        // webScoketId 的格式为 userNo_uuid, 如果找到的websocketId与自己的不同，则发送消息让对方下线
                        WebSocketSessionUtils.sendMessageLike(userNo, loginTag, JSONUtil.toJsonStr(msgMap));
                    }
                }
            }
        }
        WebSocketSessionUtils.add(webSocketId, loginTag, session);
        log.info(webSocketId + "_" + loginTag + " 加入Websocket连接");
    }

    /**
     * 收到客户端消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String webSocketId = MapUtils.getString(session.getAttributes(), "webSocketId");
        String loginTag = MapUtils.getString(session.getAttributes(), "loginTag");
        String payload = message.getPayload();
        // 如果是ping信息则回复pong
        WebSocketSessionUtils.sendMessage(webSocketId, loginTag, PING.equals(payload) ? PONG : "服务端返回：" + payload);
    }

    /**
     * 出现异常
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String webSocketId = MapUtils.getString(session.getAttributes(), "webSocketId");
        String loginTag = MapUtils.getString(session.getAttributes(), "loginTag");
        log.error("Websocket 连接异常: " + WebSocketSessionUtils.getKey(webSocketId, loginTag));
        log.error(exception.getMessage(), exception);
        WebSocketSessionUtils.remove(webSocketId, loginTag);
    }

    /**
     * 连接关闭
     *
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String webSocketId = MapUtils.getString(session.getAttributes(), "webSocketId");
        String loginTag = MapUtils.getString(session.getAttributes(), "loginTag");
        if (WebSocketSessionUtils.hasConnection(webSocketId, loginTag)) {
            WebSocketSession existSession = WebSocketSessionUtils.get(webSocketId, loginTag);
            String existSessionId = existSession.getId();
            String sessionId = session.getId();
            if (sessionId.equals(existSessionId)) {
                WebSocketSessionUtils.remove(webSocketId, loginTag);
                log.info(webSocketId + "_" + loginTag + " 断开Websocket连接");
            }
        }
    }

    /**
     * 是否分段发送消息
     *
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
