package com.scorpio.bean;

import java.util.Date;

public class TItemParam {
    private Long id;

    private Long itemCid;

    private Date created;

    private Date updated;

    private String itemData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCid() {
        return itemCid;
    }

    public void setItemCid(Long itemCid) {
        this.itemCid = itemCid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getItemData() {
        return itemData;
    }

    public void setItemData(String itemData) {
        this.itemData = itemData == null ? null : itemData.trim();
    }
}