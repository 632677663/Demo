package com.scorpio.dao;

import java.util.List;
import java.util.Map;

import com.scorpio.bean.TItemCat;

public interface TItemCatDao {

    List<TItemCat> queryList(Map<String, Object> map);
    
}