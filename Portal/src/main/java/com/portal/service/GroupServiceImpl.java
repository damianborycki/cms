package com.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.interfaces.GroupDAOI;
import com.portal.entity.Group;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDAOI groupDAO;

	public Group getGroup(Long id) {
		return groupDAO.get(id);
	}

}
