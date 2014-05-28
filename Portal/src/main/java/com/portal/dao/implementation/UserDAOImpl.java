package com.portal.dao.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.portal.dao.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.portal.entity.Group;
import com.portal.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {	
		return sessionFactory.openSession();
	}

	public User getUser(String login) {
		List<User> users = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", login);

		users = query.list();
		
		if (users != null && users.size() > 0)
			return users.get(0);
		else
			return null;	
	}
	
	public void setUserGroup(String login, Long groupId) {
		
		Query groupQuery = openSession().createQuery("from Group g where g.id = :groupId");
		groupQuery.setParameter("groupId", groupId);
		
		List<Group> groups = groupQuery.list();
		
		if (groups != null && groups.size() > 0) {
			
			Group groupToSet = groups.get(0);
			
			Query query = openSession().createQuery("update User u set u.group = :groupToSet where u.login = :login");
			query.setParameter("login", login);
			query.setParameter("groupToSet", groupToSet);
			query.executeUpdate();
		}

	}

    @Override
    public List<User> findAll() { //TODO mocked
        User example = new User();
        example.setLogin("jan123");
        example.setName("Jan");
        example.setSurname("Kowalski");
        example.setPassword("qwerty");
        example.setCity("Warszawa");
        Calendar c = Calendar.getInstance();
        c.set(2012, 3, 12);
        example.setDateOfRegistration(c.getTime());
        c.set(2013, 3, 12);
        example.setDateOfLastLogIn(c.getTime());
        example.setId(0l);
        example.setEmail("jan123@example.com");

        User example1 = new User();
        example1.setLogin("nok123");
        example1.setName("Jan");
        example1.setSurname("Nowak");
        example1.setPassword("haslo");
        example1.setCity("Kraków");
        c.set(2012, 4, 15);
        example.setDateOfRegistration(c.getTime());
        c.set(2013, 5, 14);
        example.setDateOfLastLogIn(c.getTime());
        example.setId(1l);
        example.setEmail("nok123@example.com");
        List<User> l = new ArrayList<User>(2);
        l.add(0,example);
        l.add(1,example1);
        return l;
    }

    @Override
    public void deleteUser(User user) {
        //TODO
    }

    public void addUser(User user) {
        //TODO
	}

}
