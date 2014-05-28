package com.portal.dao.interfaces;

import com.portal.entity.Tag;

import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 21:28
 */

public interface TagDAOI {

    List<Tag> findAll();
    Tag get(Long id);

}
