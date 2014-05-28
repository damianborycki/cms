package com.portal.dao.interfaces;


import com.portal.entity.Comment;

import java.util.Comparator;
import java.util.List;

public interface CommentDAOI {


    public Comment getById(long id);

    public List<Comment> get(int limit, int pageNum, Comparator<Comment> sortby, boolean asc);

    public List<Comment> get(long user_id, int limit, int pageNum, Comparator<Comment> sortby, boolean asc);

    public List<Comment> get(long articleId, long thread, int limit, int pageNum, Comparator<Comment> sortby, boolean asc);

    public List<Comment> getApprovedByUser(long userId);

    public void add(long userId, String content, Long parent, long articleId);

    public void delete(long comment_id);

    public List<Comment> children(Comment comment);
}
