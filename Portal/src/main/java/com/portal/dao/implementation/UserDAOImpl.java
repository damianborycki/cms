package com.portal.dao.implementation;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.portal.dao.interfaces.UserDAOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.portal.entity.Group;
import com.portal.entity.User;

@Repository
public class UserDAOImpl implements UserDAOI {
	
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
	
	public void setUserData(User user) {
		
		Session session = openSession();
		
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", user.getLogin());
		
		List<User> users = query.list();
		
		if (users != null && users.size() > 0) {
		
			User tempUser = (User) users.get(0);
	
			User userToUpdate = (User) session.load(User.class, tempUser.getId());
			
			Transaction transaction = session.beginTransaction();
			
			userToUpdate.setEmail(user.getEmail() != null ? user.getEmail() : tempUser.getEmail());
			userToUpdate.setPassword(user.getPassword() != null ? user.getPassword() : tempUser.getPassword());
			userToUpdate.setCity(user.getCity() != null ? user.getCity() : tempUser.getCity());
			userToUpdate.setGender(user.getGender() != null ? user.getGender() : tempUser.getGender());
			userToUpdate.setInfo(user.getInfo() != null ? user.getInfo() : tempUser.getInfo());
			userToUpdate.setName(user.getName() != null ? user.getName() : tempUser.getName());
			userToUpdate.setSurname(user.getSurname() != null ? user.getSurname() : tempUser.getSurname());
				
			userToUpdate.setDateOfLastLogIn(tempUser.getDateOfLastLogIn());
			userToUpdate.setGroup(tempUser.getGroup());
			userToUpdate.setDateOfRegistration(tempUser.getDateOfRegistration());

			try {
				session.merge(userToUpdate);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			} finally {
				transaction.commit();
				
			}
		}
		
		if (session != null)
			session.close();
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
    public void deleteUser(String login) {
        
    	Query query = openSession().createQuery("delete User u where u.login = :login");
		query.setParameter("login", login);
		
		query.executeUpdate();
		
    }

    public User addUser(User user) {
    	
        Session session = openSession();        
             
        user.setDateOfLastLogIn(null);
        user.setAvatar(null);
        user.setInfo(null);
        
        Group userGroup = (Group) session.load(Group.class, 3l);
        user.setGroup(userGroup);   
        
        MessageDigest messageDigest;
        String hashedPass = "";
        
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(user.getPassword().getBytes(), 0, user.getPassword().length());  
	        hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);  
	        
	        if (hashedPass.length() < 32) {
	           hashedPass = "0" + hashedPass; 
	        }
	        
	        user.setPassword(hashedPass);
	        
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}  

        User userToReturn = null;
        
        Transaction transaction = session.beginTransaction();
        
        try {
        	userToReturn = (User) session.load(User.class, session.save(user));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        } finally {
            transaction.commit();
            
        }
        
        if (session != null)
            session.close();
        
        return userToReturn;
    }

}
