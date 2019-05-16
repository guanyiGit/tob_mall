package com.pojo;

import com.entities.GoodsCategoryDO;

import java.util.List;

public class CascadeGoodsCategoryDO extends GoodsCategoryDO {

    private List<CascadeGoodsCategoryDO> children;

    public List<CascadeGoodsCategoryDO> getChildren() {
        return children;
    }

    public void setChildren(List<CascadeGoodsCategoryDO> children) {
        this.children = children;
    }
}
