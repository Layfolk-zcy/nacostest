package provider.component;

/**
 * @program: erukeribbon
 * @description: zipkin中的storageComponent方法调用
 * @author:
 * @create: 2022-05-17 15:42
 * <p>
 * Description 通过服务名称获取该服务中的span名称集合
 * @date 2022/5/17 15:44
 * @param: serverName
 * @return: java.util.Map<java.lang.String, java.lang.Object>
 * <p>
 * Description 通过服务名称获取该服务中的span名称集合
 * @date 2022/5/17 15:44
 * @param: serverName
 * @return: java.util.Map<java.lang.String, java.lang.Object>
 */
/*@Service
public class StorageComponentConfig {

    @Autowired(required = false)
    private  StorageComponent storage;

    *//**
 * Description 通过服务名称获取该服务中的span名称集合
 * @date 2022/5/17 15:44
 * @param: serverName
 * @return: java.util.Map<java.lang.String, java.lang.Object>
 *//*
    public Map<String, Object> getSpanNamesByServerName(String serverName) {
        Map<String, Object> hashMap = new HashMap<>();
        InMemoryStorage storageComponent = InMemoryStorage.newBuilder().build();
        ServiceAndSpanNames serviceAndSpanNames = storageComponent.serviceAndSpanNames();
        Call<List<String>> spanNames = serviceAndSpanNames.getSpanNames(serverName);
        hashMap.put("spanNames",spanNames);
//        Traces traces = storageComponent.traces();
//        Call<List<Span>> listCall = traces.getTrace("");
//        SpanConsumer spanConsumer = storageComponent.spanConsumer();
        return hashMap;
    }

}*/
