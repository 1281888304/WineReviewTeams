package Wines.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Wines.model.ProfessionalRatings;
import Wines.model.UserRatings;

public class ProfessionalRatingsDao {
	protected ConnectionManager connectionManager;

	private static ProfessionalRatingsDao instance = null;
	protected ProfessionalRatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserRatingsDao getInstance() {
		if(instance == null) {
			instance = new ProfessionalRatingsDao();
		}
		return instance;
	}

	public ProfessionalRatings create(ProfessionalRatings professionalRatings) throws SQLException {
		String insertProfessionalRatings =
			"INSERT INTO ProfessionalRatings(ProfessionalRatingsId,WineTitle,TasterName,Points) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProfessionalRatings,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, professionalRatings.getWines().getWineTitle());
			insertStmt.setString(2, professionalRatings.getTaster().getTasterName());
			insertStmt.setInt(3, professionalRatings.getRating());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			professionalRatings.setProfessionalRaingId(ratingId);
			return professionalRatings;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	public ProfessionalRatings delete(ProfessionalRatings professionalRatings) throws SQLException {
		String deleteProfessionalRating = "DELETE FROM ProfessionalRatings WHERE ProfessionalRatingsId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProfessionalRating);
			deleteStmt.setInt(1, professionalRatings.getProfessionalRaingId());
			deleteStmt.executeUpdate();

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

	
	public ProfessionalRatings getUserRatingsById(int ratingId) throws SQLException {
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE ProfessionalRatingsId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserRatings);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
			TasersDao tasersDao = TasersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			if(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingId");
				int rating = results.getInt("Rating");
				String wineTitle = results.getString("WineTitle");
				String tasterName = results.getString("TasterName");
				
				
				Tasters tasters = tasersDao.getUserByUserName(tasterName);
				Wines wines = winesDao.getRestaurantById(ratingId);
				ProfessionalRatings professionalRatings = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				return professionalRatings;
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

	
	public  List<ProfessionalRatings> getProfessionalRatingsByTasterName(String tasterName)  throws SQLException {
		List<ProfessionalRatings> professionalRatings = new ArrayList<ProfessionalRatings>();
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE TasterName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessionalRatings);
			selectStmt.setString(1, tasterName);
			results = selectStmt.executeQuery();
			TasersDao tasersDao = TasersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingId");
				int rating = results.getInt("Rating");
				String wineTitle = results.getString("WineTitle");
				String tasterName = results.getString("TasterName");

				Tasters tasters = tasersDao.getUserByUserName(tasterName);
				Wines wines = winesDao.getWineByWineTitle(wineTitle);
				ProfessionalRatings professionalRating = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				
				professionalRatings.add(professionalRating);
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
		return professionalRatings;
	}
	
	
	
	public List<ProfessionalRatings> getProfessionalRatingsByUserWineTitle(String wineTitle) throws SQLException {
		List<ProfessionalRatings> professionalRatings = new ArrayList<ProfessionalRatings>();
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessionalRatings);
			selectStmt.setString(1, wineTitle);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingId");
				int rating = results.getInt("Rating");
				String WineTitle = results.getString("WineTitle");
				String tasterName = results.getString("TasterName");

				Tasters tasters = tasersDao.getUserByUserName(tasterName);
				Wines wines = winesDao.getWineByWineTitle(wineTitle);
				ProfessionalRatings professionalRating = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				
				professionalRatings.add(professionalRating);
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
		return professionalRatings;
	}

}
