package testScripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnectionStandalone {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AutomationPractice",
				"postgres", "bingo");
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement
				.executeQuery("Select num,firstname,lastname,username from public.EMPLOYEE_BASIC_INFORMATION");

		while (resultSet.next()) {
			String empnum = resultSet.getString("num");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String username = resultSet.getString("username");
			System.out.println(empnum + "\t" + firstname + "\t" + lastname + "\t" + username);

		}
	}

}
