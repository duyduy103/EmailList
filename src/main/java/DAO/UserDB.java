package dao;

import Model.SQLresult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    public Connection getConnection() throws SQLException {
    String dburl = "jdbc:postgresql://localhost:5432/SQLGateway";
    String username = "postgres";
    String password = "123456";
    
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("PostgreSQL Driver not found", e);
    }
    
    return DriverManager.getConnection(dburl, username, password);

    }
    
   public SQLresult executeSQL(String sqlStatement) {
    SQLresult result = new SQLresult();

    Connection conn = null;
    PreparedStatement pr = null;
    ResultSet rs = null;

    try {
        conn = getConnection();
        pr = conn.prepareStatement(sqlStatement);

        if (pr.execute()) {
            result.setHasResultSet(true);

            rs = pr.getResultSet();

            ResultSetMetaData rsm = rs.getMetaData();
            int count = rsm.getColumnCount();

            for (int i = 1; i <= count; i++) {
                String column = rsm.getColumnName(i);
                result.getColumns().add(column);
            }

            while (rs.next()) {
                List<String> row = new ArrayList<>();

                for (int i = 1; i <= count; i++) {
                    Object value = rs.getObject(i);
                    row.add(value == null ? "NULL" : value.toString());
                }

                result.getRows().add(row);
            }

        } else {
            int updateCount = pr.getUpdateCount();
            result.setUpdateCount(updateCount);
        }

    } catch (SQLException e) {
        result.setErrorMessage(e.getMessage());

    } finally {
        // Đóng theo thứ tự ngược lại với lúc tạo
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // xử lý nếu cần
            }
        }

        if (pr != null) {
            try {
                pr.close();
            } catch (SQLException e) {
                // xử lý nếu cần
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // xử lý nếu cần
            }
        }
    }

    return result;
}
    

}