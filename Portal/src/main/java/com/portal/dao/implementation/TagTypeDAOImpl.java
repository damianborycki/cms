package com.portal.dao.implementation;

import com.portal.dao.interfaces.TagTypeDAOI;
import com.portal.entity.TagType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TagTypeDAOImpl implements TagTypeDAOI {
    
    @Override
    public List<TagType> findAll() {
        List<TagType> list = new ArrayList<TagType>();
        TagType exampleTagType = new TagType();
        exampleTagType.setId(0l);
        exampleTagType.setDescription("example");
        exampleTagType.setName("Best");

        list.add(exampleTagType);

        exampleTagType = new TagType();
        exampleTagType.setId(1l);
        exampleTagType.setName("New");
        exampleTagType.setDescription("example2");

        list.add(exampleTagType);

        return list;
    }

    @Override
    public TagType get(Long id) {
        TagType exampleTagType = new TagType();
        exampleTagType.setId(0l);
        exampleTagType.setDescription("example");
        exampleTagType.setName("Best");

        return exampleTagType;
    }
    
    @Override
    public void create(TagType tagType) {
        //@TODO
    }

    @Override
    public void edit(Long tagTypeId, TagType template) {
        //@TODO
    }

    @Override
    public void delete(Long id) {
        //@TODO
    }
    
}
