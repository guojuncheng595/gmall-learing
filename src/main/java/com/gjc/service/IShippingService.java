package com.gjc.service;

import com.github.pagehelper.PageInfo;
import com.gjc.common.ServerResponse;
import com.gjc.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
