package com.config;

import com.event.CustomRemoteApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/***
 * 通过发布CustomRemoteApplicationEvent来实现对服务重新启动后获取最新的配置数据
 */
@Component
public class AutoPullConfig implements ApplicationRunner {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventPublisher.publishEvent(new CustomRemoteApplicationEvent("", "", ""));
    }

}
