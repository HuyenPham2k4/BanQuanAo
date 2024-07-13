package Helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JDBCHelper {

        private static String forName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static String url = "jdbc:sqlserver://localhost:1433;databaseName=TenDatabase;encrypt=false";
        private static String user = "sa";// user sql
        private static String password = "123456789";// pass sql

        //nạp drive
        static {
            try {
                Class.forName(forName);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
            Connection connection = DriverManager.getConnection(url, user, password);
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


