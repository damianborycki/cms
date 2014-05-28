package com.portal.dao.implementation;

import com.portal.dao.interfaces.CategoryDAOI;
import com.portal.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 20:39
 */

@Component
public class CategoryDAOImpl implements CategoryDAOI {

    @Override
    public Category get(Long id) {
        Category mockCategory = new Category();
        mockCategory.setName("mock category");
        mockCategory.setDescription("utworzona w celach testowych");
        mockCategory.setId(id);
        return mockCategory;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();

        Category parent = new Category();
        parent.setName("Wiadomosci");
        parent.setDescription("utworzona w celach testowych");
        parent.setId(1l);


        Category child1 = new Category();
        child1.setName("Sport");
        child1.setDescription("utworzona w celach testowych");
        child1.setId(2l);

        Category child2 = new Category();
        child2.setName("Polityka");
        child2.setDescription("utworzona w celach testowych");
        child2.setId(3l);

        List<Category> children = new ArrayList<Category>();
        children.add(child1);
        children.add(child2);

        parent.setChildren(children);

        list.add(parent);

        parent = new Category();
        parent.setName("Inne");
        parent.setDescription("do testow");
        parent.setId(5l);

        list.add(parent);

        return list;
    }

    @Override
    public void create(Category category) {
        //@TODO
    }

    @Override
    public void edit(Long categoryId, Category template) {
        //@TODO
    }

    @Override
    public void delete(Long id) {
        //@TODO
    }

    @Override
    public List<Category> getByParentId(Long id) {
        List<Category> list = new ArrayList<Category>();
        Category category = new Category();
        category.setName("by parent id");
        category.setDescription("do testow");

        list.add(category);

        return list;
    }

    @Override
    public List<Category> getByChildId(Long id) {
        return new ArrayList<Category>();
    }
}
