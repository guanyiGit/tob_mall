package com.dao;

import com.entities.BrowseDO;
import com.pojo.BrowseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TBrowseDao {


    /**
     * 新增历史记录
     *
     * @param history
     */
    int addHistory(@Param("history") BrowseDO history);

    /**
     * 查看我的浏览历史 商品
     *
     * @param userId
     * @return
     */
    List<BrowseVo> historys(@Param("userId") Long userId);


}
