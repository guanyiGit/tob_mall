package com.pojo;

import com.entities.AttrDO;

import java.util.List;

public class VOAttr extends AttrDO {

    private List<VOAttrVal> voAttrVals;

    public List<VOAttrVal> getVoAttrVals() {
        return voAttrVals;
    }

    public void setVoAttrVals(List<VOAttrVal> voAttrVals) {
        this.voAttrVals = voAttrVals;
    }
}
