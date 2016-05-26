package com.scorpio.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scorpio.bean.EasyUIDataGridResult;
import com.scorpio.bean.TItem;
import com.scorpio.dao.TItemDao;
import com.scorpio.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private TItemDao itemDao;

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        
        //分页处理
        PageHelper.startPage(page, rows);

        HashMap<String, Object> map = new HashMap<String, Object>();
        
        List<TItem> list = itemDao.queryList(map);
        
        PageInfo<TItem> pageInfo = new PageInfo<>(list);
        
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        
        result.setRows(list);
        
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    

}
