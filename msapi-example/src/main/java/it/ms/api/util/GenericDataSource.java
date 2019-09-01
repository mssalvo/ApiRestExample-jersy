package it.ms.api.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class GenericDataSource {

	public static DataSource getDataSource() {
		Properties props = ApplicationProperty.getProperties();

		BasicDataSource ds = new BasicDataSource();

		ds.setDriverClassName(props.getProperty("api.db.postgres.driverClassName"));
		ds.setUrl(props.getProperty("api.db.postgres.jdbcUrl"));
		ds.setUsername(props.getProperty("api.db.postgres.username"));
		ds.setPassword(props.getProperty("api.db.postgres.password"));
		ds.setMaxActive(10);
		ds.setMaxIdle(5);
		ds.setInitialSize(5);
		ds.setValidationQuery("SELECT 1");

		return ds;
	}

	public static Connection driverManagerConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Properties props = ApplicationProperty.getProperties();
			DriverManager.registerDriver((Driver) Class
					.forName(ApplicationProperty.getValue("api.db.postgres.driverClassName")).newInstance());
		Connection connct = DriverManager.getConnection(props.getProperty("api.db.postgres.jdbcUrl"),
				props.getProperty("api.db.postgres.username"), props.getProperty("api.db.postgres.password"));
		return connct;
	}

}
