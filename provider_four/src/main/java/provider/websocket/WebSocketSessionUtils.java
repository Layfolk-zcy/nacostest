package provider.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * websocket 工具类
 */
public class WebSocketSessionUtils {

    private static Logger log = LoggerFactory.getLogger(WebSocketSessionUtils.class);

    private static Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    /**
     * 保存一个连接
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @param session     会话
     * @return void
     */
    public static void add(String webSocketId, String loginTag, WebSocketSession session) {
        clients.put(getKey(webSocketId, loginTag), session);
    }

    /**
     * 获取一个连接
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @return org.springframework.web.socket.WebSocketSession
     */
    public static WebSocketSession get(String webSocketId, String loginTag) {
        return clients.get(getKey(webSocketId, loginTag));
    }

    /**
     * 根据会话id获取连接
     *
     * @param sessionId
     * @return org.springframework.web.socket.WebSocketSession
     */
    public static WebSocketSession get(String sessionId) {
        return clients.get(sessionId);
    }

    public static List<WebSocketSession> getLike(String userNo, String loginTag) {
        List<String> needKey = clients.keySet().stream().filter(tmp -> tmp.startsWith(userNo + "_") && tmp.endsWith("_" + loginTag)).collect(Collectors.toList());
        List<WebSocketSession> sessionList = new ArrayList<>();
        needKey.forEach(tmp -> {
            sessionList.add(clients.get(tmp));
        });
        return sessionList;
    }

    /**
     * 移除一个连接
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @return void
     */
    public static void remove(String webSocketId, String loginTag) throws IOException {
        clients.remove(getKey(webSocketId, loginTag));
    }

    /**
     * 根据sessionId移除连接
     *
     * @param sessionId
     * @return void
     */
    public static void remove(String sessionId) throws IOException {
        clients.remove(sessionId);
    }

    /**
     * 组装sessionId
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @return java.lang.String
     */
    public static String getKey(String webSocketId, String loginTag) {
        return webSocketId + "_" + loginTag;
    }

    /**
     * 判断是否有效连接
     * 判断是否存在
     * 判断连接是否开启
     * 无效的进行清除
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @return boolean
     */
    public static boolean hasConnection(String webSocketId, String loginTag) {
        String key = getKey(webSocketId, loginTag);
        if (clients.containsKey(key)) {
            return true;
        }
        return false;
    }

    /**
     * 判断已建立连接中是否有
     * 以 startStr 开头
     * 以 loginTag 结尾
     * 的连接
     *
     * @param startStr
     * @param loginTag
     * @return boolean
     */
    public static boolean hasConnectionLike(String startStr, String loginTag) {
        // 判断是否有以startStr开头 以loginTag结尾的连接  省略中间的token
        return clients.keySet().stream().anyMatch(tmp -> tmp.startsWith(startStr + "_") && tmp.endsWith("_" + loginTag));
    }

    /**
     * 获取连接数的数量
     *
     * @param
     * @return int
     */
    public static int getSize() {
        return clients.size();
    }

    /**
     * 发送消息到客户端
     * 该方法原先被调用传入的参数为userNo,以userNo_loginTag进行拼接，以原先方式传入参数会找不到建立连接的客户端会话
     * 可以改为调用sendMessageLike方法，或者传入以userNo_tokenUUID_loginTag 拼接的webSocketId
     *
     * @param webSocketId websocket id
     * @param loginTag    登录标志
     * @param message     消息内容
     * @return void
     */
    public static void sendMessage(String webSocketId, String loginTag, String message) {
        if (!hasConnection(webSocketId, loginTag)) {
            throw new RuntimeException(getKey(webSocketId, loginTag) + " 连接不存在");
        }
        WebSocketSession session = get(webSocketId, loginTag);
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            log.error("Websoket发送消息异常: " + getKey(webSocketId, loginTag));
            log.error(e.getMessage(), e);
            clients.remove(getKey(webSocketId, loginTag));
        }
    }

    /**
     * 对已经建立的格式为 userNo_tokenUUID_loginTag 的连接会话发送客户端请求
     * 对于单点登录的用户，只可能存在一个以userNo_uuid_loginTag 的会话，所以只有userNo和loginTag的前提下可以通过该方法
     * 对唯一用户进行发送，如果非单点登录的情况下，则会对所有登录的该用户发送，可能会有问题产生
     *
     * @param userNo
     * @param loginTag
     * @param message
     * @return void
     */
    public static void sendMessageLike(String userNo, String loginTag, String message) {
        if (!hasConnectionLike(userNo, loginTag)) {
            throw new RuntimeException(getKey(userNo, loginTag) + " 连接不存在");
        }
        List<String> clientSessionIdList = clients.keySet().stream()
                // 将所有以startStr 和 loginTag结尾的收集
                .filter(tmp -> tmp.startsWith(userNo + "_") && tmp.endsWith("_" + loginTag))
                .collect(Collectors.toList());
        for (String sessionId : clientSessionIdList) {
            WebSocketSession session = get(sessionId);
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("Websoket发送消息异常: " + sessionId);
                log.error(e.getMessage(), e);
                clients.remove(sessionId);
            }
        }
    }

    /**
     * 发送消息给所有在线的
     *
     * @param message
     * @throws Exception
     */
    public static void sendMessageAllOnline(String message) throws Exception {
        if (clients.size() == 0) {
            throw new NullPointerException("当前没有用户在线");
        }
        try {
            for (String key : clients.keySet()) {
                WebSocketSession session = clients.get(key);
                session.sendMessage(new TextMessage(message));
            }
        } catch (IOException e) {
            log.error("Websoket发送消息异常: " + message);
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 发送消息给指定用户
     *
     * @param list
     * @param message 消息内容
     * @return void
     */
    @SuppressWarnings("rawtypes")
    public static void sendMessageToAppoint(ArrayList list, String message) throws Exception {
        if (clients.size() == 0) {
            throw new NullPointerException("当前没有用户在线");
        }
        if (list.isEmpty()) {
            log.warn("发送消息对象为空");
            throw new NullPointerException("没有指定对象");
        }
        try {
            for (String key : clients.keySet()) {
                for (int i = 0, n = list.size(); i < n; i++) {
                    String arrId = String.valueOf(list.get(i));
                    if (key.equals(arrId)) {
                        WebSocketSession session = clients.get(key);
                        session.sendMessage(new TextMessage(message));
                    }
                }
            }
        } catch (IOException e) {
            log.error("Websocket发送消息异常");
            log.error(e.getMessage(), e);
        }
    }
}
