package it.ms.api.filter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.ms.api.util.ApplicationProperty;
import it.ms.api.util.GenericDataSource;

public class AuthenticatedTables {

	private AuthenticatedTables() {
	}

	static {
		System.out.println("AuthenticatedTables jdbc-driver :: " + ApplicationProperty.getValue("api.db.postgres.driverClassName"));
	}

	public static boolean isPresent(String user, String password) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = GenericDataSource.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM apps where user_id='" + user + "' AND app_key='" + password + "'");
			System.out.println("resultSet first:: " + resultSet.first());
			boolean responsBool = resultSet.first();

			return responsBool;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
				if (statement != null && !statement.isClosed())
					statement.close();
				if (resultSet != null && !resultSet.isClosed())
					resultSet.close();
			} catch (SQLException e) {

			}

		}

		return false;
	}

}
