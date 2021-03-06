package com.portal.dao.interfaces;


import com.portal.entity.Comment;
import com.portal.util.ClassComment;
import com.portal.util.ClassParentComment;

import java.util.ArrayList;
import java.util.List;

public interface CommentDAOI {


    public Comment getById(long id);

    public List<Comment> get(int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> get(long user_id, int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> get(long articleId, long thread, int limit, int pageNum, String sortby, boolean asc);

    public List<Comment> getApprovedByUser(long userId);

    public boolean add(String login, String content, Long parent, long articleId);

    public void deleteCascade(long comment_id);

    public List<Comment> children(Comment comment);
    
    public void setStates(List<Comment> comments);
    
    public void delete(List<Comment> comments);
    
    public ClassComment getUserComments(String login, int limit, int pageNO, String sortOrder);
    
    public ClassComment getUserComments(String login, long status, int limit, int pageNo, String sortOrder);
    
    public ClassParentComment getArticleComments(long articleID, int limit, int pageNO, String sortOrder);
    
    public ClassComment getAllComments(long status, int limit, int pageNo, String sortOrder);
    
    public ClassComment getAllComments(int limit, int pageNo, String sortOrder);
    
    public long getTotalComments(long status);   
    
}
