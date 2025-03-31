package com.guess.it.core.utils;

import java.util.HashMap;
import java.util.Map;

public class PageHandle {

    public static Map<String, Object> page(int totalItem, int pageSize, Object items) {
        Map<String, Object> data = new HashMap<>();
        data.put("total_items", totalItem);
        data.put("total_pages", (int) Math.ceil((double) totalItem / pageSize));
        data.put("list", items);
        return data;
    }
}
