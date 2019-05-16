package com.pojo;

import com.entities.AttrDO;
import com.entities.SkuDO;

public class AttrRefSkuDo extends AttrDO {
    private SkuDO skuDO;

    public SkuDO getSkuDO() {
        return skuDO;
    }

    public void setSkuDO(SkuDO skuDO) {
        this.skuDO = skuDO;
    }
}
