package com.portal.dao.implementation;

import com.portal.dao.interfaces.TagTypeDAOI;
import com.portal.entity.TagType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagTypeDAOImpl implements TagTypeDAOI {
    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

    private Session openSession() {
        return sessionFactory.openSession();
    }
	
	private Criteria getCriteria(int limit, int pageNum,
			String sortby, boolean asc){
		Criteria criteria=this.openSession().createCriteria(TagType.class,"t");
		if(asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum*limit);
		criteria.setMaxResults(limit);
		return criteria;
	}
	
    @Override
    public List<TagType> findAll() {
    	Criteria criteria = this.openSession().createCriteria(TagType.class);
    	return criteria.list();
    }

    @Override
    public TagType get(Long id) {
        return (TagType) this.openSession().load(TagType.class, id);
    }
    
    @Override
    public void create(TagType tagType) {
        this.openSession().save(tagType);
    }

    @Override
    public void edit(Long tagTypeId, TagType template) {
        Session session = openSession();
        TagType tagType = (TagType) session.get(TagType.class, tagTypeId);
        tagType.setName(template.getName());
        tagType.setDescription(template.getDescription());

        session.update(tagType);
        session.flush();
    }

    @Override
    public void delete(Long id) {
        Session session = openSession();
        TagType tagType = (TagType) session.get(TagType.class, id);
        session.delete(tagType);
        session.flush();
    }
    
}
