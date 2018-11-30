package com.gjc.service;

import com.gjc.common.ServerResponse;
import com.gjc.vo.CartVo;

public interface ICartService {

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
