package com.portal.dao.interfaces;

import com.portal.entity.Group;

import java.util.List;

public interface GroupDAOI {
	
	public Group get(Long id);
    public List<Group> findAll();
    public void add(Group group);
    public void edit(Group group, String name, String description);
    public void delete(Group group);
}
