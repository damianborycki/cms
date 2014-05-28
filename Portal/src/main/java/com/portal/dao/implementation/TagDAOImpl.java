package com.portal.dao.implementation;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 21:29
 */

@Component
public class TagDAOImpl implements TagDAOI {

    @Override
    public List<Tag> findAll() {
        List<Tag> list = new ArrayList<Tag>();
        Tag exampleTag = new Tag();
        exampleTag.setId(0l);
        exampleTag.setDescription("example");
        exampleTag.setName("Best");

        list.add(exampleTag);

        exampleTag = new Tag();
        exampleTag.setId(1l);
        exampleTag.setName("New");
        exampleTag.setDescription("example2");

        list.add(exampleTag);

        return list;
    }

    @Override
    public Tag get(Long id) {
        Tag exampleTag = new Tag();
        exampleTag.setId(0l);
        exampleTag.setDescription("example");
        exampleTag.setName("Best");

        return exampleTag;
    }
}
