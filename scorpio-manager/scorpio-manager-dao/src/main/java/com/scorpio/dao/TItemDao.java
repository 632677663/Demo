package com.scorpio.dao;

import java.util.List;
import java.util.Map;

import com.scorpio.bean.TItem;

public interface TItemDao {
    
    List<TItem> queryList(Map<String, Object> map);
    
}