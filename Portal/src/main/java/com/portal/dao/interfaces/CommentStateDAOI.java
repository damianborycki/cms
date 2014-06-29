package com.portal.dao.interfaces;

import com.portal.entity.CommentState;

import java.util.List;

/**
 * Created by Mateusz on 2014-06-25.
 */
public interface CommentStateDAOI {
    public CommentState get(Long id);
    public List<CommentState> findAll();
    public void add(CommentState commentState);
    public void edit(CommentState commentState, String name, String description);
    public void delete(CommentState commentState);
}
