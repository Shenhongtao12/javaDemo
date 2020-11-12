package com.sht.apimybatis.service;

import com.sht.apimybatis.dto.GoodsDto;
import com.sht.apimybatis.entity.Goods;

public interface GoodsService {

    public GoodsDto findById(int id);

    GoodsDto findByOne(int id);
}
