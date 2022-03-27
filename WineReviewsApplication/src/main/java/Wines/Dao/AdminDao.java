package blog;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Admin table in your
 * MySQL instance. This is used to store {@link Admin} into your MySQL instance and 
 * retrieve {@link Admin} from MySQL instance.
 */
public class AdminDao extends PersonDao {
	// Single pattern: instantiation is limited to one object.
	private static AdminDao instance = null;
	protected AdminDao() {
		super();
	}
	public static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	public Admin create(Admin administrator) throws SQLException {
		// Insert into the superclass table first.
		create(new Person(administrator.getUserName(), administrator.getFirstName(),
			administrator.getLastName()));

		String insertAdministrator = "INSERT INTO Admin(UserName, FirstName, LastName, Password, LastLogin) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setString(1, administrator.getUserName());
			insertStmt.setString(2, administrator.getFirstName());
			insertStmt.setString(3, administrator.getLastName());
			insertStmt.setString(4, administrator.getPassword());
			insertStmt.setTimestamp(5, new Timestamp(administrator.getLastLogin().getTime()));
			insertStmt.executeUpdate();
			return administrator;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}


	/**
	 * Delete the Admin instance.
	 * This runs a DELETE statement.
	 */
	public Admin delete(Admin administrator) throws SQLException {
		String deleteAdministrator = "DELETE FROM Admin WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setString(1, administrator.getUserName());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Admin first.
			super.delete(administrator);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public Admin getAdministratorFromUserName(String userName) throws SQLException {
		// To build an Administrator object, we need the Person record, too.
		String selectAdministrator =
			"SELECT Admin.UserName AS UserName, FirstName, LastName, Password, LastLogin " +
			"FROM Admin INNER JOIN Person " +
			"  ON Admin.UserName = Person.UserName " +
			"WHERE Admin.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrator);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString(userName);
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String password = results.getString("Password");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Admin administrator = new Admin(resultUserName, firstName, lastName, password, lastLogin);
				return administrator;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

}
