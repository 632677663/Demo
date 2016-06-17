package com.scorpio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scorpio.bean.EasyUIDataGridResult;
import com.scorpio.bean.ScorpioResult;
import com.scorpio.bean.TItem;
import com.scorpio.service.ItemService;

@Controller
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/item/list")
    @ResponseBody
    private EasyUIDataGridResult getItemList(Integer page,Integer rows){
        
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        
        return itemList;
    }
    
    @RequestMapping(value="/item/save",method = RequestMethod.POST)
    @ResponseBody
    private ScorpioResult addItem(TItem item,String desc){
        return itemService.saveItem(item, desc);
    }
    
}
