package com.portal.dao.interfaces;


import com.portal.entity.Comment;

import java.util.Comparator;
import java.util.List;

public interface CommentDAOI {


    public Comment getById(long id);

    public List<Comment> get(int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> get(long user_id, int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> get(long articleId, long thread, int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> getApprovedByUser(long userId);

    public void add(long userId, String content, Long parent, long articleId);

    public void deleteCascade(long comment_id);

    public List<Comment> children(Comment comment);
    
    public void setStates(List<Comment> comments);
    
    public void delete(List<Comment> comments);
    
    public List<Comment> getUserComments(long userID, int limit, int pageNO, String sortOrder);
    
    public List<Comment> getArticleComments(long articleID, int limit, int pageNO, String sortOrder);
}
