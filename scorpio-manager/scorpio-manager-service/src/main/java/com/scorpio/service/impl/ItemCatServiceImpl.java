package com.scorpio.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorpio.bean.EasyUITreeNode;
import com.scorpio.bean.TItemCat;
import com.scorpio.dao.TItemCatDao;
import com.scorpio.service.ItemCatService;
/**
 * 商品分类管理Service
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    
    @Autowired
    private TItemCatDao itemCatDao;

    @Override
    public List<EasyUITreeNode> queryItemCatList(Long parentId) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("parentId", parentId);
        
        List<TItemCat> list = itemCatDao.queryList(map);
        
        List<EasyUITreeNode> treeNodes = new ArrayList<EasyUITreeNode>();
        
        for(TItemCat o : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(o.getId());
            node.setText(o.getName());
            node.setState(o.getIsParent() ? "closed" : "open");
            treeNodes.add(node);
        }
        return treeNodes;
    }

    

}
