package provider.controller;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ServiceBean
 * @projectName: erukeribbon
 * @description: servicebean
 * @date: 2022-07-04 11:13
 **/
public class ServiceBean {
    /**
     * 服务名
     */
    private String serviceId;
    /**
     * 实例名 分隔符;
     */
    private String instanceId;
    /**
     * host 分隔符;
     */
    private String host;
    /**
     * ip 分隔符;
     */
    private String ipAddr;
    /**
     * 端口 分隔符;
     */
    private String port;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
