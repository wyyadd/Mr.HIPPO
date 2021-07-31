package com.hippo.fresh.alipay.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author: Dai Junfeng
 * @create: 2021-04-09
 */
public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> jsonToMap(String jsonStr) throws JsonProcessingException {
        Map<String, Object> map = mapper.readValue(jsonStr, Map.class);
        return map;
    }
}
