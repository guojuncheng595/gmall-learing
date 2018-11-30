package com.gjc.service;

import com.github.pagehelper.PageInfo;
import com.gjc.common.ServerResponse;
import com.gjc.pojo.Product;
import com.gjc.vo.ProductDetailVo;

public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,
                                                         Integer categoryId,
                                                         int pageNum,
                                                         int pageSize,
                                                         String orderBy);
}
