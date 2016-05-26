package com.scorpio.service;

import java.util.List;

import com.scorpio.bean.EasyUITreeNode;

public interface ItemCatService {

    public List<EasyUITreeNode> queryItemCatList(Long parentId);
    
}
