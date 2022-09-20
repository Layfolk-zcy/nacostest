/*
package provider.sleuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.instrument.messaging.DefaultMessageSpanCustomizer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: MyMessageSpanCustomizer
 * @projectName: sungrid-consoleops
 * @description: spanx定制器
 * @date: 2022-08-10 09:02
 **//*

@Component
public class MyMessageSpanCustomizer extends DefaultMessageSpanCustomizer {

    @Value("${spring.application.logyname:}")
    private String belLogy;
    @Value("${spring.application.appname:}")
    private String belApp;
    @Value("${server.port:}")
    private String port;
    @Value("${spring.application.name:}")
    private String belSer;
    @Value("${server.port:}")
    private String localEndPointPort;
    @Value("${eureka.instance.ip-address:}")
    private String localEndPointIp;

    @Override
    public Span.Builder customizeReceive(Span.Builder spanCustomizer, Message<?> message, MessageChannel messageChannel) {
        return super.customizeReceive(spanCustomizer, message, messageChannel)
                .tag("providerOne-Receive", "providerOne-Receive")
                .tag("belLogy", belLogy)
                .tag("belApp", belApp)
                .tag("belSer", belSer)
                .tag("serverName", belSer + port)
                .tag("localEndPointIp", localEndPointIp)
                .tag("localEndPointPort", localEndPointPort);
    }

    @Override
    public Span.Builder customizeSend(Span.Builder spanCustomizer, Message<?> message, MessageChannel messageChannel) {
        return super.customizeSend(spanCustomizer, message, messageChannel)
                .tag("providerOne-Send", "providerOne-Send")
                .tag("belLogy", belLogy)
                .tag("belApp", belApp)
                .tag("belSer", belSer)
                .tag("serverName", belSer + port)
                .tag("localEndPointIp", localEndPointIp)
                .tag("localEndPointPort", localEndPointPort);
    }
}
*/
