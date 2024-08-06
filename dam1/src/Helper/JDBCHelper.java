package Helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JDBCHelper {

//        private static String forName = "jdbc:sqlserver://localhost:1436;databaseName= thithu;encrypt=true;trustServerCertificate=true";
        private static String url = "jdbc:sqlserver://localhost:1436;databaseName= QL_BAN_QUAN_AO2;encrypt=true;trustServerCertificate=true";
        private static final String username = "sa";
        private static final String password = "1Secure*Password1"; // phụ thuộc tài khoản đăng nhập azude

        public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
            Connection connection = DriverManager.getConnection(url, username, password);
//        System.out.println("Kết nối thành công");
            PreparedStatement pstmt = null;
            if (sql.trim().startsWith("{")) {
                pstmt = connection.prepareCall(sql);
            } else {
                pstmt = connection.prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt;
        }

        public static void executeUpdate(String sql, Object... args) {
            try {
                PreparedStatement stmt = prepareStatement(sql, args);
                try {
                    stmt.executeUpdate();
//                stmt.e
                } finally {
                    stmt.getConnection().close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static ResultSet executeQuery(String sql, Object... args) {
            try {
                PreparedStatement stmt = prepareStatement(sql, args);
                return stmt.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


