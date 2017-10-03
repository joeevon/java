package mysql_test;

import com.mysql.cj.jdbc.PreparedStatement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

/**
 * Created by joee on 2017/10/3.
 */


public class mysql_test {
    private static Log log = LogFactory.getLog(mysql_test.class);
    private static final String URL = "jdbc:mysql://172.17.134.132:4407/yiai_online";
    private static final String NAME = "root";
    private static final String PASSWORD = "yiai_dev";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);

            String sql = "select teacherid, courseid from t_pay_record where tradeno = '20171003-155347-1507017227277'";
//            String sql = "select teacherid, courseid from t_pay_record";
            log.info("mysql:" + sql);
            Statement pstmt = (Statement) conn.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            log.info("row:" + rs.getRow());

            while (rs.next()) {
                log.info("here.");
                log.info("teacherid:" + rs.getInt(1) + ",courseid:" + rs.getInt(2));
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            log.error(e.getStackTrace());
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
    }
}
