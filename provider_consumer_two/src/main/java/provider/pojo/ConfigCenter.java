package provider.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @program: erukeribbon
 * @description: 配置中心数据获取
 * @author:
 * @create: 2022-06-02 09:45
 **/

//@ConfigurationProperties(prefix = ConfigCenter.APPLICATION_NAME)
@Component
@RefreshScope
public class ConfigCenter {

    @Value("${spring.application.name}")
    public static String applicationName;

//    public static final String APPLICATION_NAME = applicationName;

}
