import java.sql.*;

public class JDBCDemo2 {

	public static void main(String[] args) {
		Connection conn = null;

		try { 
			String dbURL = "jdbc:sqlserver://localhost;"+ "instanceName=MSSQLSERVER;databaseName=university;user=sa;password=csis3300";
			conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				// prepared statements that have parameters, useful for your project
				PreparedStatement prepStmt1 = null;		
				String selectSQL = "SELECT ID, name FROM instructor WHERE dept_name = ?";
				prepStmt1 = conn.prepareStatement(selectSQL);
				prepStmt1.setString(1, "Biology");
				ResultSet rs = prepStmt1.executeQuery();

				while ( rs.next() ) {
					System.out.println(rs.getString("ID") + " " + rs.getString("name"));

				}
				
				System.out.println("..................");
				
				PreparedStatement prepStmt2 = null;		// prepared statements
				String insertSQL = "INSERT INTO instructor VALUES(?, ?, ?, ?)";
				prepStmt2 = conn.prepareStatement(insertSQL);
				prepStmt2.setString(1, "88877");
				prepStmt2.setString(2, "Perry");
				prepStmt2.setString(3, "Finance");
				prepStmt2.setInt(4, 125000);
				prepStmt2.executeUpdate();
				
				while (rs.next()) {
					System.out.println(rs.getString("ID") + " " + rs.getString("name"));
				}
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}
