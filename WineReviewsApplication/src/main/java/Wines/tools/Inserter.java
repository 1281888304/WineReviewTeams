package Wines.tools;

import Wines.Dao.*;
import Wines.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PersonDao personDao = PersonDao.getInstance();
		TastersDao tastersDao=TastersDao.getInstance();
		
		
		
//		AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
//		BlogUsersDao blogUsersDao = BlogUsersDao.getInstance();
//		BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
//		BlogCommentsDao blogCommentsDao = BlogCommentsDao.getInstance();
//		ResharesDao resharesDao = ResharesDao.getInstance();
		
		// INSERT objects from our model.
		Person person = new Person("b", "bruce", "chhay");
		person = personDao.create(person);
		Person person1 = new Person("b1", "bruce", "chhay");
		person1 = personDao.create(person1);
		Person person2 = new Person("b2", "bruce", "chhay");
		person2 = personDao.create(person2);
		
		//insert tasters
		

		
		// READ.
		Person p1 = personDao.getPersonFromUserName("b");
		List<Person> pList1 = personDao.getPersonFromFirstName("bruce");
		System.out.format("Reading person: u:%s f:%s l:%s \n",
			p1.getUserName(), p1.getFirstName(), p1.getLastName());
		for(Person p : pList1) {
			System.out.format("Looping Person: u:%s f:%s l:%s \n",
				p.getUserName(), p.getFirstName(), p.getLastName());
		}

	}
}
