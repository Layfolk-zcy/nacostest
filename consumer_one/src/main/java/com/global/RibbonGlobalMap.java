package com.global;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class RibbonGlobalMap {
    /**
     * 存储配置文件中ribbon策略的标识数据
     */
    public static final Map<String, String> RIBBON_STRATEGY_MAP = new ConcurrentHashMap<>();
}
