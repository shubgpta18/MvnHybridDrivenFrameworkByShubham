package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
	Statement statement;

	public DataBaseConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AutomationPractice", "postgres",
					"bingo");
			statement = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeSelectQuery(String sql) {
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdateQuery(String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int executeInsertQuery(String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int executeDeleteQuery(String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
//		while (resultSet.next()) {
//			String empnum = resultSet.getString("num");
//			String firstname = resultSet.getString("firstname");
//			String lastname = resultSet.getString("lastname");
//			String username = resultSet.getString("username");
//			System.out.println(empnum+"\t"+firstname+"\t"+lastname+"\t"+username);

}
