package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LocationDao {
	protected ConnectionManager connectionManager;

	private static LocationDao instance = null;
	protected LocationDao() {
		connectionManager = new ConnectionManager();
	}
	public static LocationDao getInstance() {
		if(instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}

	public Location create(Location location) throws SQLException {
		String insertLocation =
			"INSERT INTO Location(Country, Province, Regin1, Regin2, WineryName) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLocation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, location.getCountry());
			insertStmt.setString(2, location.getProvince());
			insertStmt.setString(3, location.getRegin1());
			insertStmt.setString(4, location.getRegin2());
			insertStmt.setString(5, location.getWineryName());
			insertStmt.executeUpdate();
			

			resultKey = insertStmt.getGeneratedKeys();
			int locationId = -1;
			if(resultKey.next()) {
				locationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			location.setLocationId(locationId);
			return location;
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


	public Location updateLocation(Location location, String newCountry, String newProvince, String newRegin1, String newRegin2, String newWineryName) throws SQLException {
		String updateLocation = "UPDATE Location SET Country=?, Province=?, Regin1=?, Regin2=?, WineryName=? WHERE LocationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newCountry);
			updateStmt.setString(2, newProvince);
			updateStmt.setString(3, newRegin1);
			updateStmt.setString(4, newRegin2);
			updateStmt.setString(5, newWineryName);
			updateStmt.setInt(6, location.getLocationId());
			updateStmt.executeUpdate();

			location.setCountry(newCountry);
			location.setProvince(newProvince);;
			location.setRegin1(newRegin1);;
			location.setRegin2(newRegin2);;
			location.setWineryName(newWineryName);
			
			return location;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	public Location delete(Location location) throws SQLException {
		String deleteLocation = "DELETE FROM Location WHERE LocationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocation);
			deleteStmt.setInt(1, location.getLocationId());
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


	public Location getLocationById(int locationId) throws SQLException {
		String selectLocation =
			"SELECT Country, Province, Regin1, Regin2, WineryName" +
			"FROM Location " +
			"WHERE LocationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setInt(1, locationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultLocationId = results.getInt("LocationId");
				String country = results.getString("Country");
				String province = results.getString("Province");
				String regin1 = results.getString("Regin1");
				String regin2 = results.getString("Regin2");
				String wineryName = results.getString("WineryName");
						
				Location location = new Location(resultLocationId, country, province, regin1, regin2, wineryName);
				return location;
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