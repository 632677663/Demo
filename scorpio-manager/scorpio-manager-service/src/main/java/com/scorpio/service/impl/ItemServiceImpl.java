package com.scorpio.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scorpio.bean.EasyUIDataGridResult;
import com.scorpio.bean.ScorpioResult;
import com.scorpio.bean.TItem;
import com.scorpio.bean.TItemDesc;
import com.scorpio.dao.TItemDao;
import com.scorpio.dao.TItemDescDao;
import com.scorpio.service.ItemService;
import com.scorpio.utils.IDUtils;
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private TItemDao itemDao;
    @Autowired
    private TItemDescDao itemDescDao;


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

    @Override
    public ScorpioResult saveItem(TItem item, String desc) {
         
         //生成商品id
         long id = IDUtils.genItemId();
         //补全item属性
         item.setId(id);
         //商品状态 1-正常 2-下架 3-删除
         item.setStatus((byte)1);
         //创建时间
         item.setCreated(new Date());
         //插入商品表
         itemDao.insert(item);
         //添加商品描述
         TItemDesc itemDesc = new TItemDesc();
         itemDesc.setId(id);
         itemDesc.setItemDesc(desc);
         itemDescDao.insert(itemDesc);
         return ScorpioResult.ok();
    }

    

}
