package com.scorpio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scorpio.bean.EasyUITreeNode;
import com.scorpio.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    
    @Autowired
    private ItemCatService itemCatService;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemList(@RequestParam(value="id",defaultValue="0") Long parentId){
        
        //List<EasyUITreeNode> treeNodes = itemCatService.queryItemCatList(id);
        
        return itemCatService.queryItemCatList(parentId);
    }

}
