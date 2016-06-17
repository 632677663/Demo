package com.scorpio.service;

import com.scorpio.bean.EasyUIDataGridResult;
import com.scorpio.bean.ScorpioResult;
import com.scorpio.bean.TItem;

public interface ItemService {
    
    
    public EasyUIDataGridResult getItemList(int page,int rows);
    
    public ScorpioResult saveItem(TItem item,String desc);
    
}
