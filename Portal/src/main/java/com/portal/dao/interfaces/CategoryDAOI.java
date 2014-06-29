package com.portal.dao.interfaces;

import com.portal.entity.Category;

import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 20:42
 */

public interface CategoryDAOI {

    public Category get(Long id);
    public List<Category> findAll();
    public List<Category> getByParentId(Long id);
    public List<Category> getByChildId(Long id);
    public void create(Category category);
    public void edit(Long categoryId, Category template);
    public void delete(Long id);

}
