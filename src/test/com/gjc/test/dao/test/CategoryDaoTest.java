package com.gjc.dao.test;

import com.gjc.dao.CategoryMapper;
import com.gjc.pojo.Category;
import com.gjc.service.impl.ICategoryServiceImpl;
import com.gjc.test.TestBase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by geely
 */
public class CategoryDaoTest extends TestBase {


    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ICategoryServiceImpl iCategoryService;

    @Ignore
    @Test
    public void getCategoryChild() {
        Category d = categoryMapper.selectByPrimaryKey(1);
        System.out.println(d);
        Category d4 = categoryMapper.selectByPrimaryKey(4);
        System.out.println(d4);
    }

    @Test
    public void testChildService() {
        iCategoryService.selectCategoryAndChildrenById(2);
    }

}
