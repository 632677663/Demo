package com.scorpio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.scorpio.bean.EasyUIDataGridResult;
import com.scorpio.bean.TItem;
import com.scorpio.bean.TItemExample;
import com.scorpio.bean.TItemExample.Criteria;
import com.scorpio.mapper.TItemMapper;
import com.scorpio.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private TItemMapper itemMapper;

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        
        //分页处理
        PageHelper.startPage(page, rows);
        
        TItemExample example = new TItemExample();
        
        List<TItem> list = itemMapper.selectByExample(example);
        
        EasyUIDataGridResult pageInfo = new EasyUIDataGridResult();
        
        pageInfo.setRows(list);
        pageInfo.setTotal(pageInfo.getTotal());

        return null;
    }

    

}
