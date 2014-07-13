package com.portal.dao.implementation;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.portal.dao.interfaces.UserDAOI;
import com.portal.init.ClassUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.portal.entity.Comment;
import com.portal.entity.Group;
import com.portal.entity.User;

@Repository
public class UserDAOImpl implements UserDAOI {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MailSender mailSender;
	
	public User getUser(String login) {
		List<User> users = new ArrayList<User>();
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from User u where u.login = :login");
		query.setParameter("login", login);
		
		users = query.list();
		
		try {
		if (users != null && users.size() > 0)
			return users.get(0);
		else
			return null;	
		} finally {
			if (session != null)
				session.close();
		}
		
	}
	
	public void setLastLoginDate(String login) {
		
		Session session = sessionFactory.openSession();
		
		try {
			Query query = session.createQuery("update User u set u.dateOfLastLogIn = :date where u.login = :login");
			query.setParameter("login", login);
			query.setParameter("date", new Date());
			query.executeUpdate();
		} finally {
			if (session != null)
				session.close();
		}
		
	}
	
	public void setUserGroup(String login, Long groupId) {
		
		Session session = sessionFactory.openSession();
		
		Query groupQuery = session.createQuery("from Group g where g.id = :groupId");
		groupQuery.setParameter("groupId", groupId);
		
		List<Group> groups = groupQuery.list();
		
		try {
			if (groups != null && groups.size() > 0) {
				
				Group groupToSet = groups.get(0);
				
				Query query = session.createQuery("update User u set u.group = :groupToSet where u.login = :login");
				query.setParameter("login", login);
				query.setParameter("groupToSet", groupToSet);
				query.executeUpdate();
			}
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	public void setUserData(User user) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from User u where u.login = :login");
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
	
	public User getLoggedUser() {
		
		Session session = sessionFactory.openSession();
		
		try {
			
			org.springframework.security.core.userdetails.User user = 
			(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Query query = session.createQuery("from User u where u.login = :login");
			query.setParameter("login", user.getUsername());
			
			List<User> users = query.list();
			
			User usr = users.get(0);
			usr.setAvatar(null);
			usr.setCity(null);
			usr.setDateOfRegistration(null);
			usr.setDateOfLastLogIn(null);
			usr.setEmail(null);
			usr.setGender(null);
			usr.setId(null);
			usr.setName(null);
			usr.setInfo(null);
			usr.setPassword(null);
			usr.setSurname(null);
			
			usr.getGroup().setDescription(null);
			usr.getGroup().setName(null);
			
			usr.setDateOfRegistration(null);
		
			return usr;
		
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			
			return null;
		} finally {
			if (session != null)
				session.close();
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
    public void deleteUser(String login) {
        
    	Session session = sessionFactory.openSession();
    	
    	try {
	    	Query query = session.createQuery("delete User u where u.login = :login");
			query.setParameter("login", login);
			
			query.executeUpdate();
    	} finally {
    		if (session != null)
				session.close();
    	}
		
    }

    public User addUser(User user, boolean withActivation) {
    	
        Session session = sessionFactory.openSession();       
             
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
        	
        	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom("portal@portal.uj.edu.pl");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("Aktywacja konta w serwisie Portal UJ");
            simpleMailMessage.setText("Aby aktywować swoje konto kliknij w poniższy odnośnik:");
            
            mailSender.send(simpleMailMessage);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        } finally {
            transaction.commit();
            
        }
        
        if (session != null)
            session.close();
        
        return userToReturn;
    }
	

    public ClassUser getAllUsers(int limit, int pageNo, final String sortBy, final String sortOrder){ 
    	
    	Session session = sessionFactory.openSession();
    	
    	List<User> u = new ArrayList<User>();		
		Criteria c = session.createCriteria(User.class);		
		u = c.list();
		
		List<User> users = new ArrayList<User>();
	  	Criteria criteria = session.createCriteria(User.class);
	  	criteria.setFirstResult(limit*(pageNo-1));
		criteria.setMaxResults(limit);
		if(sortOrder.equals("DESC")){
			criteria.addOrder(Order.desc(sortBy));	
		} else {
			criteria.addOrder(Order.asc(sortBy));
		}
		users = criteria.list();
		
		for( User rU : users ){
			rU.setPassword(null);
		}
		ClassUser classUser = new ClassUser();
		classUser.setUsers(users);
		classUser.setSize(u.size());
		
		if (session != null)
            session.close();
		
		return classUser;
    }
    
    public int totalUsers(){ 
    	
    	Session session = sessionFactory.openSession();
    	
    	try {
	    	Query query = session.createQuery("from User");		
			return query.list().size(); 
    	} finally {
    		if (session != null)
                session.close();
    	}
    }



	@Override
	public boolean loginExists(User login) {
		
		Session session = sessionFactory.openSession();
		
		List<User> u = new ArrayList<User>();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login.getLogin()));
		u = criteria.list();
		
		if (session != null)
            session.close();
		
		if(u.size() == 0)
			return false;
			
		return true;
	}



	@Override
	public boolean emailExists(User email) {
		List<User> u = new ArrayList<User>();
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email.getEmail()));
		u = criteria.list();
		
		if (session != null)
            session.close();
		
		if(u.size() == 0)
			return false;
		
		return true;
	}
}
