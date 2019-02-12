package com.gjc.service.test;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.gjc.common.ServerResponse;
import com.gjc.service.IProductService;
import com.gjc.test.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by geely
 */
public class ProductServiceTest extends TestBase {

    @Autowired
    private IProductService iProductService;

    @Test
    public void testIProductService(){
        ServerResponse<PageInfo> result =  iProductService.getProductByKeywordCategory("iphone",2,1,5,"price_desc");
        System.out.println(result);
    }

    public static void main(String[] args) {
        List<String> images = Lists.newArrayList();
        images.add("gmall/aa.jpg");
        images.add("gmall/bb.jpg");
        images.add("gmall/cc.jpg");
        images.add("gmall/dd.jpg");
        images.add("gmall/ee.jpg");
//        ["mmall/aa.jpg","mmall/bb.jpg","mmall/cc.jpg","mmall/dd.jpg","mmall/ee.jpg"]
    }
}
