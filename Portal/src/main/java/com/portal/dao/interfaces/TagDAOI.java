package com.portal.dao.interfaces;

import com.portal.entity.Tag;
import com.portal.entity.TagType;

import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 21:28
 */

public interface TagDAOI {

    public List<Tag> findAll();
    public Tag get(Long id);
    public List<Tag> getByIds(List<Long> ids);
    public void create(Tag tag);
    public void edit(Long id, Tag template);
    public void delete(Long id);
    public boolean existsForType(TagType tagType);

}
