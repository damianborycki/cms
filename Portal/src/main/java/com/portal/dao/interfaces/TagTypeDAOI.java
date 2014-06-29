package com.portal.dao.interfaces;

import com.portal.entity.TagType;
import java.util.List;

public interface TagTypeDAOI {
    
    public List<TagType> findAll();
    public TagType get(Long id);
    public void create(TagType category);
    public void edit(Long tagTypeId, TagType template);
    public void delete(Long id);
    
}
