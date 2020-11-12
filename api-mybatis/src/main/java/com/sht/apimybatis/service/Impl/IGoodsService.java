package com.sht.apimybatis.service.Impl;

import com.sht.apimybatis.Mapper.GoodsMapper;
import com.sht.apimybatis.dto.GoodsDto;
import com.sht.apimybatis.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IGoodsService implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsDto findById(int id) {
        return goodsMapper.findById(id);
    }

    @Override
    public GoodsDto findByOne(int id) {
        return goodsMapper.findByOne(id);
    }
}
